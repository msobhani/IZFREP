package de.com.izf.network;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;
import de.com.izf.R;

public class ImageDownloader{
	
    ImageView bmImage;
    Context ctx;
    String URL;
    String fileName;
    
    private Bitmap resizeImage(Bitmap img)
    {
    	int scale = Math.max(img.getWidth(), img.getHeight()) / 100;
    	if(scale != 0)
    		img = Bitmap.createScaledBitmap(img,(int)(img.getWidth()/scale), (int)(img.getHeight()*0.8)/scale, true);
    	return img;
    }
    
    private String getName(String URL)
    {
    	String ans [] = URL.split("/");
    	return ans[ans.length - 1];
    }
    
    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
            Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }
    
    
    
    private void saveToInternalSorage(Bitmap bitmapImage) throws Exception
    {
    	
    	ContextWrapper cw = new ContextWrapper(ctx);
    	File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
    	File file=new File(directory,fileName);
        FileOutputStream fos = null;
        try {           
            fos = new FileOutputStream(file);
       // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        setImag();
    }
    
    public void setImag() throws Exception
    {
    	ContextWrapper cw = new ContextWrapper(ctx);
    	File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
    	File mypath=new File(directory,fileName);

       if(mypath.exists())
       {
        try {
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(mypath));
            bmImage.setImageBitmap(b);
        } 
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        }
       }
       else
       {
    	   asyncImageDownloader dlImg = new asyncImageDownloader();
    	   try {
    		   //Bitmap img = 
    		   dlImg.execute(URL);
    		  
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       }

    }
    
    public ImageDownloader(ImageView bmImage, Context ctx , String URL) {
        this.bmImage = bmImage;
        this.ctx = ctx;
        this.URL = URL;
        this.fileName = getName(URL);
    }
    
    private class asyncImageDownloader extends AsyncTask<String, Void, Bitmap>
    {
    	protected Bitmap doInBackground(String... urls)
    	{
	        Bitmap mIcon11 = null;
	        try {
	        	java.net.URL url = new java.net.URL(urls[0]);
	        	java.net.URI uri = new java.net.URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
	        	url = uri.toURL();
	        	
	        	
	            InputStream in = url.openStream();
	            mIcon11 = BitmapFactory.decodeStream(in);
	        } catch (Exception e) {
	            Log.e("Error", e.getMessage());
	            e.printStackTrace();
	        }
	        finally
	        {	
	        	return mIcon11;
	        }
	    }

	    protected void onPostExecute(Bitmap result) {
	    	if(result != null)
	    	{
	    		//result = resizeImage(result);
	    		//bmImage.setImageBitmap(result);
	    		result = resizeImage(result);
 			   	try {
					saveToInternalSorage(result);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
	    	else
	    		bmImage.setImageBitmap(
	    		BitmapFactory.decodeResource(ctx.getResources(), R.drawable.no_icon));
	    }
    }
}












