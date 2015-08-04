package com.sreekanth.duelist;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.Toast;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.DropboxAPI.DropboxInputStream;
import com.dropbox.client2.DropboxAPI.Entry;
import com.dropbox.client2.exception.DropboxException;
import com.dropbox.client2.exception.DropboxIOException;
import com.dropbox.client2.exception.DropboxParseException;
import com.dropbox.client2.exception.DropboxPartialFileException;
import com.dropbox.client2.exception.DropboxServerException;
import com.dropbox.client2.exception.DropboxUnlinkedException;



public class DownloadRandomPicture extends AsyncTask<Void, String, String>
{
    private Activity mContext;
    private final ProgressDialog mDialog;
    private DropboxAPI<?> mApi;
    private String mPath;
    ArrayList<Entry> thumbs = new ArrayList<Entry>();
    ArrayList<DropBoxBean> dbb = new ArrayList<DropBoxBean>();
    private FileOutputStream mFos;
    String result = "";
    private boolean mCanceled;
    private Long mFileLen;
    private String mErrorMsg;
    ContentValues values;
    private String mFile;
 //   private final static String IMAGE_FILE_NAME = "0069165pm.csv";

    public DownloadRandomPicture(Activity context, DropboxAPI<?> api,String dropboxPath,String  FILE_NAME)
    {
        mContext = context;
        mApi = api;
        mPath = dropboxPath;
        mFile = FILE_NAME;
        mDialog = new ProgressDialog(context);
        mDialog.setMessage("Downloading File");
        mDialog.setIndeterminate(false);
       
        mDialog.setMax(100);
        mDialog.setCancelable(true);
       
        mDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mDialog.setButton("Cancel", new OnClickListener()
        {
        	public void onClick(DialogInterface dialog, int which)
        	{
                mCanceled = true;
                mErrorMsg = "Canceled";
                
                if (mFos != null)
                {
                	try
                	{
                        mFos.close();
                    }
                	catch (IOException e)
                	{
                    }
                }
            }
        });
 //       mDialog.show();
    }
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		mDialog.show();
	}
    @Override
    protected String doInBackground(Void... params)
    {
        try
        {
            if (mCanceled)
            {
                return null;
            }
            Entry dirent = mApi.metadata(mPath, 1000, null, true, null);
            if (!dirent.isDir || dirent.contents == null)
            {
                // It's not a directory, or there's nothing in it
                mErrorMsg = "File or empty directory";
                return null;
            }

            for (Entry ent: dirent.contents) {
            	if(ent.fileName().endsWith(".csv"))
            	{
            		thumbs.add(ent);
            		String url = "dl.dropbox.com/u/" + mApi.accountInfo().uid + "/" + ent.fileName();
             		if (ent.fileName().equals(mFile)){mFileLen = ent.bytes;System.out.println(mFileLen);
             		System.out.println("Public URL: "+url);
                  };
            		dbb.add(new DropBoxBean(ent.fileName(),url));               	
      //      	System.out.println("Entries from dropbox: "+mContext.getCacheDir().getAbsolutePath() +""+ent.path);
            	}
            }
            if (mCanceled) {
                return null;
            }
            BufferedInputStream br = null;
            BufferedOutputStream bw = null;
            String unzipLocation = Environment.getExternalStorageDirectory() + "/download/"+ mFile;            
            try
            {
          
            	 File localFile = new File(unzipLocation);
            	            if (!localFile.exists()) {
            	                localFile.createNewFile(); //otherwise dropbox client will fail silently
            	            }
              
            }
            catch (FileNotFoundException e)
            {
                mErrorMsg = "Couldn't create a local file to store the image";
                return null;
            }
          /******************************************/
            DropboxInputStream din = mApi.getFileStream("/Photos/"+mFile, null);
            br = new BufferedInputStream(din);
            bw = new BufferedOutputStream(new FileOutputStream(unzipLocation));

            byte[] buf = new byte[1024];
            int read = 0;
            long total = 0;
            while((read=br.read(buf, 0, buf.length))!=-1) {
            	bw.write(buf, 0, read);
            	  total += read;
                    // publishing the progress....
                    // After this onProgressUpdate will be called
                    publishProgress(""+(int)((total*100)/mFileLen));
            }
            br.close();
            bw.close(); 
       /***************************************************/     
            if (mCanceled) {
                return null;
            }
            return null;
        } catch (DropboxUnlinkedException e) {
            // The AuthSession wasn't properly authenticated or user unlinked.
        } catch (DropboxPartialFileException e) {
            // We canceled the operation
            mErrorMsg = "Download canceled";
        } catch (DropboxServerException e) {
            // Server-side exception.  These are examples of what could happen,
            // but we don't do anything special with them here.
            if (e.error == DropboxServerException._304_NOT_MODIFIED) {
                // won't happen since we don't pass in revision with metadata
            } else if (e.error == DropboxServerException._401_UNAUTHORIZED) {
                // Unauthorized, so we should unlink them.  You may want to
                // automatically log the user out in this case.
            } else if (e.error == DropboxServerException._403_FORBIDDEN) {
                // Not allowed to access this
            } else if (e.error == DropboxServerException._404_NOT_FOUND) {
            	System.out.println("File Is Not Found: " + e);
                // path not found (or if it was the thumbnail, can't be
                // thumbnailed)
            } else if (e.error == DropboxServerException._406_NOT_ACCEPTABLE) {
                // too many entries to return
            } else if (e.error == DropboxServerException._415_UNSUPPORTED_MEDIA) {
                // can't be thumbnailed
            } else if (e.error == DropboxServerException._507_INSUFFICIENT_STORAGE) {
                // user is over quota
            } else {
                // Something else
            }
            // This gets the Dropbox error, translated into the user's language
            mErrorMsg = e.body.userError;
            if (mErrorMsg == null) {
                mErrorMsg = e.body.error;
            }
        } catch (DropboxIOException e) {
            // Happens all the time, probably want to retry automatically.
            mErrorMsg = "Network error.  Try again." + e;
        } catch (DropboxParseException e) {
            // Probably due to Dropbox server restarting, should retry
            mErrorMsg = "Dropbox error.  Try again.";
        } catch (DropboxException e) {
            // Unknown error
            mErrorMsg = "Unknown error.  Try again.";
        } catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
        return null;
    }
    @Override
    protected void onProgressUpdate(String... progress) {
        // setting progress percentage
        mDialog.setProgress(Integer.parseInt(progress[0]));
   }
    @Override
    protected void onPostExecute(String result) {
        mDialog.dismiss();
        if (result != null)
        {
        	Iterator<DropBoxBean> iter = dbb.iterator();
        	while(iter.hasNext())
        	{
        		DropBoxBean d = iter.next();
        		System.out.println("File: " + d.getFileName() + ". URL: " + d.getPublicURL());
        	} 
        } else {
         // Couldn't download it, so show an error
      //        showToast(mErrorMsg);
        }
        }
    private void showToast(String msg) {
        Toast error = Toast.makeText(mContext, msg, Toast.LENGTH_LONG);
        System.out.println(msg);
        error.show();
    }
}