package com.ebrain.util;

import java.lang.reflect.Type;
import java.math.RoundingMode;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class Util {

	private static DecimalFormat df2 = new DecimalFormat(".##");
	private static Double loyalFanPercentage = 0.0;
	private static final String CHARACTER_SET = "0123456789abcdefghijkmnpqrstuvwxyzABCDEFGHIJKLMNPQRSTUVWXYZ";
	private static Random rnd = new Random();
	private static Integer maxLength = 8;
	public static String basePath = "////C://Tomcat 7.0//webapps//";
	
	
	public static GsonBuilder getGsonBuilder(){
		GsonBuilder builder = new GsonBuilder(); 

		// Register an adapter to manage the date types as long values 
		builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() { 
		   public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		      return new Date(json.getAsJsonPrimitive().getAsLong()); 
		   } 
		});
		return builder;
	}
	
	
	
	public static String getFileNameFromPath(String filePath) throws Exception {
		String fileName = "";
		if(filePath!=null && !filePath.isEmpty()){
			fileName = filePath.substring(filePath.lastIndexOf("/")+1, filePath.length());
		}
		return fileName;
	}
	
	public static String generatePassword(String fisrtName, String prefix) throws Exception{
		if(fisrtName.length() > 3){
			fisrtName = fisrtName.substring(0,3);
		}
		String uniqueString = UUID.randomUUID().toString();
		String sub = uniqueString.substring(0,4);
		String key = doBase64Encryption(sub+fisrtName);
		key =key.replaceAll("=", "");
		String code = "RTF"+key;
		return code;
	}
	
	public static String doBase64Encryption(String originalText) {
		byte[] encodedBytes = Base64.encodeBase64(originalText.getBytes());
		String encryptedText = new String(encodedBytes);
		return encryptedText;
	}
	
	public static String generateEncrptedPassword(String clearPassword) {
	     MessageDigest algorithm;
	  try {
	   algorithm = MessageDigest.getInstance("SHA-1");
	  } catch (NoSuchAlgorithmException e) {
	   throw new RuntimeException("SHA-1 algorithm not found!");
	  }
	     byte [] digest = algorithm.digest(clearPassword.getBytes());
	     String encrptedPw = new String(Hex.encodeHex(digest));
	     return encrptedPw;
	 }

	public static String generateCustomerReferalCodeOld(Long referrerNumber,
			String prefix) throws Exception {
		String uniqueString = UUID.randomUUID().toString();
		String sub = uniqueString.substring(0, 4);
		String key = doBase64Encryption(sub + referrerNumber);
		key = key.replaceAll("=", "");
		String code = prefix + key;
		return code;
	}
	
	public static String generateCustomerReferalCode() throws Exception{
		StringBuilder builder = new StringBuilder();
		String tempCharSet = new String(CHARACTER_SET);
	    for(int i = 0; i < maxLength; i++){
	        builder.append(tempCharSet.charAt(rnd.nextInt(tempCharSet.length())));
	    }
	    return builder.toString().toUpperCase();
	}
	
	
	
	public static Double getRoundedValue(Double value) throws Exception {
		df2.setRoundingMode(RoundingMode.DOWN);
		return Double.valueOf(df2.format(value));
	}
	
	/*public static String getObject(Map<String, String> dataMap,String url) throws Exception{
		try{
			HttpClient client = HttpClientBuilder.create().build();
			HttpPost post = new HttpPost(url);

			// add header
			post.setHeader("x-sign", Constants.X_SIGN_HEADER);
			post.setHeader("x-token", Constants.X_TOKEN_HEADER);
			post.setHeader("x-platform", Constants.X_PLATFORM_HEADER);			
			post.setHeader("Accept","application/json");
			
			List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
			
			for(Map.Entry<String, String> entry : dataMap.entrySet()){
				urlParameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
			
			post.setEntity(new UrlEncodedFormEntity(urlParameters));

			HttpResponse response = client.execute(post);

			BufferedReader responseData = new BufferedReader(
			        new InputStreamReader(response.getEntity().getContent()));		
			
			String result = IOUtils.toString(responseData);
			System.out.println(result);
			return result;

		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getObjectWithFile(Map<String, String> dataMap,String url,List<String> files) throws Exception{
		try{
			HttpClient client = HttpClientBuilder.create().build();
			HttpPost post = new HttpPost(url);

			// add header
			post.setHeader("x-sign", Constants.X_SIGN_HEADER);
			post.setHeader("x-token", Constants.X_TOKEN_HEADER);
			post.setHeader("x-platform", Constants.X_PLATFORM_HEADER);			
			post.setHeader("Accept","application/json");
			
			MultipartEntity multiEntity = new MultipartEntity();
			for(Map.Entry<String, String> entry : dataMap.entrySet()){
				multiEntity.addPart(entry.getKey(),new StringBody(entry.getValue()));
			}
			
			if(files!=null && !files.isEmpty()){
				int i = 1;
				for(String file : files){
					multiEntity.addPart("file_"+i,new FileBody(new File(file)));
					i++;
				}
				multiEntity.addPart("fileSize",new StringBody(String.valueOf(i-1)));
			}
			post.setEntity(multiEntity);
			HttpResponse response = client.execute(post);

			BufferedReader responseData = new BufferedReader(
			        new InputStreamReader(response.getEntity().getContent()));		
			
			String result = IOUtils.toString(responseData);
			System.out.println(result);
			return result;

		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}*/
	
	public static String formatDoubleToTwoDecimalPoint(Double value){
		String result="";
		NumberFormat formatter = new DecimalFormat("#0.00");     
		try {
			if(value!=null){
				result = formatter.format(value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String formatDateToMonthDateYear(Date date){
		String result = "";
		DateFormat formatDate = new SimpleDateFormat("MM/dd/yyyy");
		try{
			if(date != null){
				result = formatDate.format(date);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public static String formatTimeToHourMinutes(Time time){
		String result = "";
		DateFormat formatTime = new SimpleDateFormat("hh:mm a");
		try{
			if(time != null){
				result = formatTime.format(time);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public static String formatDateTimeToMonthDateYearAndHourMinute(Date datetime){
		String result = "";
		DateFormat formatDateTime = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		try{
			if(datetime != null){
				result = formatDateTime.format(datetime);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public static String getDateWithTwentyFourHourFormat(Date datetime){
		String result = "";
		DateFormat formatDateTime = new SimpleDateFormat("yyyy/MM/dd/ HH:mm:ss");
		try{
			if(datetime != null){
				result = formatDateTime.format(datetime);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public static Date getDateWithTwentyFourHourFormat(String datetime){
		Date result =null;
		DateFormat formatDateTime = new SimpleDateFormat("yyyy/MM/dd/ HH:mm:ss");
		try{
			if(datetime != null){
				result = formatDateTime.parse(datetime);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public static String getParseDateWithTwelvwFourHourFormat(String datetime){
		String result =null;
		DateFormat formatDateTime = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
		try{
			if(datetime != null){
				result = formatDateTime.format(formatDateTime.parse(datetime));
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	
	public static String formatDateTimeToMonthDateYearWithTwelveHourFormat(Date datetime){
		String result = "";
		DateFormat formatDateTime_12Hour = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
		try{
			if(datetime != null){
				result = formatDateTime_12Hour.format(datetime);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	
	public static String getFilePath(HttpServletRequest request,String fileName,String folderPath){
		String path = null;
		try {
			if(null != request){
				path = request.getSession().getServletContext().getRealPath(folderPath+fileName);
			}else{
				//path = URLUtil.apiServerBaseUrl+folderPath+fileName;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return path;
	}
	
	
	
	public static Integer extractDateElement(String input,String format){
		try {
			if(input==null || input.isEmpty() || format==null || format.isEmpty()){
				return -1;
			}
			if(format.equals("DAY")){
				if(input.contains("/")){
					String arr[] = input.split("/");
					if(arr.length>1){
						return Integer.parseInt(arr[1].isEmpty()?"-1":arr[1]);
					}
				}
				return -1;
			}else if(format.equals("MONTH")){
				if(input.contains("/")){
					String arr[] = input.split("/");
					if(arr.length>0){
						return Integer.parseInt(arr[0].isEmpty()?"-1":arr[0]);
					}
				}else{
					return Integer.parseInt(input);
				}
				return -1;
			}else if(format.equals("YEAR")){
				if(input.contains("/")){
					String arr[] = input.split("/");
					if(arr.length>2){
						return Integer.parseInt(arr[2].isEmpty()?"-1":arr[2]);
					}
				}
				return -1;
			}else if(format.equals("HOUR")){
				String amPm="";
				if(input.contains(":")){
					String arr[];
					if(input.contains(" ")){
						arr = input.split(" ");
						amPm = arr[1];
					}
					arr = input.split(":");
					if(arr.length>0){
						if(amPm.equalsIgnoreCase("pm")){
							return Integer.parseInt(arr[0].isEmpty()?"-1":arr[0])+12;
						}
						return Integer.parseInt(arr[0].isEmpty()?"-1":arr[0]);
					}
				}else{
					return Integer.parseInt(input);
				}
				return -1;
			}else if(format.equals("MINUTE")){
				if(input.contains(":")){
					if(input.contains(" ")){
						input= input.substring(0,input.indexOf(" "));
					}
					String []arr = input.split(":");
					if(arr.length>1){
						return Integer.parseInt(arr[1].isEmpty()?"-1":arr[1]);
					}
				}
				return -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public static Double roundOffDouble(Double input){
		Double roundedValue = 0.00;
		DecimalFormat df = new DecimalFormat("#.##");
		try{
			if(input != null && input > 0){		
				roundedValue = input;		
				roundedValue = Double.valueOf(df.format(roundedValue));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return roundedValue;
	}
	
	public static String formatTime(String eventTimeStr){
		String formattedTime = null;
		try {
			if(eventTimeStr != null){
				if(eventTimeStr.equals("TBD")){
					formattedTime = "TBD";
				}else{
					SimpleDateFormat sdf = new SimpleDateFormat("hh:mmaa");
					DateFormat tf = new SimpleDateFormat("hh:mm aa");
					
					Date date = (Date)sdf.parse(eventTimeStr);
					formattedTime = tf.format(date);
				}
			}
			return formattedTime;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Date getDateAndTime(Date date,Time time){
		if(date==null && time==null){
			return null;
		}
		Calendar aDate = Calendar.getInstance();
		Calendar aTime = Calendar.getInstance();
		Calendar aDateTime = Calendar.getInstance();
		if(date!=null){
		    aDate.setTime(date);
		    aDateTime.set(Calendar.DAY_OF_MONTH, aDate.get(Calendar.DAY_OF_MONTH));
		    aDateTime.set(Calendar.MONTH, aDate.get(Calendar.MONTH));
		    aDateTime.set(Calendar.YEAR, aDate.get(Calendar.YEAR));
		    aDateTime.set(Calendar.HOUR_OF_DAY, 0);
		    aDateTime.set(Calendar.MINUTE, 0);
		    aDateTime.set(Calendar.MILLISECOND, 0);
		    aDateTime.set(Calendar.SECOND, 0);
		}
		if(time!=null){
		    aTime.setTime(time);
		    aDateTime.set(Calendar.HOUR_OF_DAY, aTime.get(Calendar.HOUR));
		    aDateTime.set(Calendar.MINUTE, aTime.get(Calendar.MINUTE));
		    aDateTime.set(Calendar.MILLISECOND, 0);
		    aDateTime.set(Calendar.SECOND, 0);
		}
	    return aDateTime.getTime();
	}
	
	public static boolean isEmptyOrNull(String str){
		if(str==null || str.trim().isEmpty()){
			return true;
		}
		return false;
	}
	
	public static String getClientIp(HttpServletRequest request) {

        String remoteAddr = "";

        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }

        return remoteAddr;
    }
	

	public static String fetchIcons(String iconName){
		String logoImage = "";
		try{
		logoImage = basePath+"AppFiles//"+iconName+".png";
		}catch(Exception e){
			e.printStackTrace();
		}
		return logoImage;
	}
	
	
	 
	
	
}