package encryption;
 
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.HashMap;
import java.util.Map;
 
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
 
 
public class SHA256withRSAUtil {
	public static void main(String[] args) {
		Map<String, Object> keyPair = generateKeyPair(2048);
		System.out.println(keyPair);
		
	}
	
	
	/**
	 * 生成公钥，私钥
	 * 
	 * @param keySize
	 *            the size of key : 1024, 2048
	 * @return Map
	 */
	public static Map<String, Object> generateKeyPair(int keySize) {
		Map<String, Object> rtn = new HashMap<String, Object>();
 
		try {
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(keySize);
			KeyPair keyPair = keyPairGenerator.generateKeyPair();
			byte[] publicKey = keyPair.getPublic().getEncoded();
			byte[] privateKey = keyPair.getPrivate().getEncoded();
 
			String publicStr = base64ToStr(publicKey);
			System.out.println("公钥字符串----------------\n" + publicStr);
			String privateStr = base64ToStr(privateKey);
			System.out.println("私钥字符串----------------\n" + privateStr);
			
			rtn.put("PUBLICK_KEY", publicKey);
			rtn.put("PRIVATE_KEY", privateKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtn;
	}
	public static String base64ToStr(byte[] b) {
		return javax.xml.bind.DatatypeConverter.printBase64Binary(b);
	}
	
	/**
     * 生成DESede算法秘钥的二进制数组
     * @param keyLen 秘钥长度，支持：56、64两种长度
     * @return 秘钥数组
     */
    public static byte[] generateKey(int keyLen) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
            keyGenerator.init(keyLen);
            SecretKey secretKey = keyGenerator.generateKey();
            return secretKey.getEncoded();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}