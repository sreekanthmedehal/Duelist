package com.sreekanth.duelist;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import android.os.Environment;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
 
public class PDFTextParser {
 

 
// PDFTextParser Constructor
public PDFTextParser() {
}

// Extract text from PDF Document
public void parsePdf(String pdf, String txt) throws IOException {
	File exportDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
	
	String f = Environment.getExternalStorageDirectory() + "/download/" + pdf; 
System.out.println("Parsing text from PDF file " + pdf + "....");
//File f = new File(fileName);
 

System.out.println("\nWriting PDF text to output text file " + txt + "....");
try {
	
File	file = new File(exportDir, txt);
    file.createNewFile();          
    String Ofile = Environment.getExternalStorageDirectory() + "/download/" + txt; 
    
    
    PdfReader reader = new PdfReader(f);
    PdfReaderContentParser parser = new PdfReaderContentParser(reader);
    PrintWriter out = new PrintWriter(new FileOutputStream(Ofile));
    TextExtractionStrategy strategy;
    for (int i = 1; i <= reader.getNumberOfPages(); i++) {
        strategy = parser.processContent(i, new SimpleTextExtractionStrategy());
        out.println(strategy.getResultantText());
    }
    out.flush();
    out.close();
    reader.close();
	

} catch (Exception e) {
System.out.println("An exception occured in writing the pdf text to file.");
e.printStackTrace();
}
System.out.println("Done.");
}
}
