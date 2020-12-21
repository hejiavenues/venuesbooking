package cn.cashbang.core.common.utils;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.spec.PKCS8EncodedKeySpec;

public class RSAUtils {
	 /** *//** 
     * 加密算法RSA 
     */  
    public static final String KEY_ALGORITHM = "RSA";  
    /** *//** 
     * RSA最大解密密文大小 
     */  
    private static final int MAX_DECRYPT_BLOCK = 128;  
  

  
    /** *//** 
     * <P> 
     * 私钥解密 
     * </p> 
     *  
     * @param encryptedData 已加密数据 
     * @param privateKey 私钥(BASE64编码) 
     * @return 
     * @throws Exception 
     */  
    public static byte[] decryptByPrivateKey(byte[] encryptedData, String privateKey)  
            throws Exception {  
        byte[] keyBytes = Base64Utils.decodeByte(privateKey);  
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);  
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);  
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);  
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());  
        cipher.init(Cipher.DECRYPT_MODE, privateK);  
        int inputLen = encryptedData.length;  
        ByteArrayOutputStream out = new ByteArrayOutputStream();  
        int offSet = 0;  
        byte[] cache;  
        int i = 0;  
        // 对数据分段解密  
        while (inputLen - offSet > 0) {  
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {  
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);  
            } else {  
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);  
            }  
            out.write(cache, 0, cache.length);  
            i++;  
            offSet = i * MAX_DECRYPT_BLOCK;  
        }  
        byte[] decryptedData = out.toByteArray();  
        out.close();  
        return decryptedData;  
    }  
  
    public static void main(String[] args) {
    	try {
    		String s="ghxyz2l3TNPL4fu9ysjm6b3Be6R/4J9eGFXdAGwOWaws/YsxRIacW2q4XejBrDi+9hh7hnkbHcI4l/TmC+BVZIFKHQhaL1kDe/32clWXPvaf+Y0+XPdVnQTqKAeWKyeOMFfyGOpgOZaWmEwzoceZEI6BYDRhC7KncLZrcF+wSnRpILosMmBBS/MzCiWeZl9nN3bIYR3sHv4J5F8jm5A+gVdTVwHhnGa3ItIyp6963LBh0dL3NeynNw4ruR4MbnktckJVnweyan7N2tvYemlRpHjeczi4Na83wn9U6UB/WnQt+I9WPQ5vd1cc5ub3yN2HfDHiGxTOGwWUrlt03yNFYHzxU0oizTbeeEevkBmL87Nao4i2Ts+5Vt5kyLfYIM+TQvDo9U9Ja36ufMYt3HpoYAb+AlTVKJX9XOQbDgM5vgCSuW4SsD5ZnT0d/clFTWewegXE1vRjSFzcqi4oigLhn02OI8EkOoq8WYoqToGoFchHn5hDXe12A63EFw9wkIQ6PenF8HzofGN9ZiUXdbrsSuO+Sw4Z1WCfgJrJEMbGZWiR7eVcAyGOAUT70mvSTze8bPCUmiJBd2n3dQGPnhlMFYYh3VrzVCveHMA4TXHK/PWTv53+hLhfNwoTQo/08nomLUccg5U/+nv6GeLrfE1aK3JUr+jZgh7YNSAxIM3sCOtMev4Fbx5b7Pt42NTKD2giLxKF5ntEFyNQEGE2W2/Gu2nZla0uvZtNw6PG0waOi7qESXqSVlUfJioKmDJu/aZs/qSldP5+ElDhqgekWjFBgl3x3CTcLJQntkMiR41K362+DVuSHj+KJKcRqkqke/OatrEF2TVAVJnbovl0QDPUSQ==";
    		byte[] c=decryptByPrivateKey(Base64Utils.decodeByte(s), "密钥");
    		System.out.println(new String(c));
    	}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
