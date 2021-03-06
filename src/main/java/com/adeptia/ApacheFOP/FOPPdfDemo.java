package com.adeptia.ApacheFOP;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;

public class FOPPdfDemo {

    public static void main(String[] args) {
        try {
        	
        	//fOPPdfDemo.convertToFO();
        	String templatePath="D:\\Temp\\template.xsl";
        	String xmlSource="D:\\Temp\\Employees.xml";
        	String pdfPath="D:\\Temp\\employee.pdf";
			System.out.println( "Hello World1!" );
        	FOPPdfDemo.convertToPDF( );
       
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    /**
     * Method that will convert the given XML to PDF 
     * @throws IOException
     * @throws FOPException
     * @throws TransformerException
     */
    public static void convertToPDF()    {
        // the XSL FO file
        File xsltFile = new File("D:\\Temp\\template.xsl");
        // the XML file which provides the input
        StreamSource xmlSource = new StreamSource(new File("D:\\Temp\\Employees.xml"));
        // create an instance of fop factory
        System.out.println(xmlSource.toString());
        FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
        // a user agent is needed for transformation
        FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
        // Setup output
        OutputStream out = null;
       /* try {
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
    
        try {
        	out = new java.io.FileOutputStream("D:\\Temp\\employee.pdf");
            // Construct fop with desired output format
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);

            // Setup XSLT
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(xsltFile));

            // Resulting SAX events (the generated FO) must be piped through to FOP
            Result res = new SAXResult(fop.getDefaultHandler());

            // Start XSLT transformation and FOP processing
            // That's where the XML is first transformed to XSL-FO and then 
            // PDF is created
            transformer.transform(xmlSource, res);
        } 
        catch(Exception e){
        	
        }
        finally {
            try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
    
    /**
     * This method will convert the given XML to XSL-FO
     * @throws IOException
     * @throws FOPException
     * @throws TransformerException
     */
    public void convertToFO()  throws IOException, FOPException, TransformerException {
        // the XSL FO file
        File xsltFile = new File("D:\\Temp\\template.xsl");
            
        /*TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(new StreamSource
          ("F:\\Temp\\template.xsl"));*/
        
        // the XML file which provides the input
        StreamSource xmlSource = new StreamSource(new File("D:\\Temp\\Employees.xml"));
        
        // a user agent is needed for transformation
        /*FOUserAgent foUserAgent = fopFactory.newFOUserAgent();*/
        // Setup output
        OutputStream out;
        
        out = new java.io.FileOutputStream("D:\\Temp\\temp.fo");
    
        try {
            // Setup XSLT
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(xsltFile));

            // Resulting SAX events (the generated FO) must be piped through to FOP
            //Result res = new SAXResult(fop.getDefaultHandler());

            Result res = new StreamResult(out);

            //Start XSLT transformation and FOP processing
            transformer.transform(xmlSource, res);


            // Start XSLT transformation and FOP processing
            // That's where the XML is first transformed to XSL-FO and then 
            // PDF is created
            transformer.transform(xmlSource, res);
        } finally {
            out.close();
        }
    }
}
