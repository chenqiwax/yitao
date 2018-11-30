package cn.et.yitao.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.SecureRandom;

/***
 * AES加密
 * @author chenwei
 *
 */
public class AESCipher {
  //加密算法
	private static final String ALGORITHM = "AES";
	
	//KEY
	public static final String KEY="0D3CED9BEC10A777AEC23CCC353A8C08";
	//加密密码长度，越长则越难破解
    private static final int KEY_SIZE = 128;
    //加密
	public static String encrypt(String key, String src) throws Exception {
		byte[] result = encrypt(src.getBytes(), key);
		return toHex(result);
	}

    //解密
	public static String decrypt(String key, String encrypted) throws Exception {
		byte[] result = decrypt(fromHex(encrypted), key);
		return new String(result);
	}

	/**
	 * <p>
	 * 生成随机密钥
	 * </p>
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String getSecretKey() throws Exception {
		return getSecretKey(null);
	}

	/**
	 * <p>
	 * 生成密钥
	 * </p>
	 * 
	 * @param seed
	 *            密钥种子
	 * @return
	 * @throws Exception
	 */
	public static String getSecretKey(String seed) throws Exception {
		KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
		SecureRandom secureRandom;
		if (seed != null && !"".equals(seed)) {
			secureRandom = new SecureRandom(seed.getBytes());
		} else {
			secureRandom = new SecureRandom();
		}
		keyGenerator.init(KEY_SIZE, secureRandom);
		SecretKey secretKey = keyGenerator.generateKey();
		return toHex(secretKey.getEncoded());
	}

	/**
	 * <p>
	 * 加密
	 * </p>
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] data, String key) throws Exception {
		Key k = toKey(fromHex(key));
		byte[] raw = k.getEncoded();
		SecretKeySpec secretKeySpec = new SecretKeySpec(raw, ALGORITHM);
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
		return cipher.doFinal(data);
	}

	/**
	 * <p>
	 * 解密
	 * </p>
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] data, String key) throws Exception {
		Key k = toKey(fromHex(key));
		byte[] raw = k.getEncoded();
		SecretKeySpec secretKeySpec = new SecretKeySpec(raw, ALGORITHM);
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
		return cipher.doFinal(data);
	}

	/**
	 * <p>
	 * 转换密钥
	 * </p>
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	private static Key toKey(byte[] key) throws Exception {
		SecretKey secretKey = new SecretKeySpec(key, ALGORITHM);
		return secretKey;
	}

	/**
	 * 将二进制转换成16进制
	 * 
	 * @param buf
	 * @return
	 */
	public static String toHex(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 将16进制转换为二进制
	 * 
	 * @param hexStr
	 * @return
	 */
	public static byte[] fromHex(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

	
	public static void main(String[] args) throws Exception {
		String content = "本站对于md5、sha1、mysql、ntlm等的实时解密成功率在全球遥遥领先。成立10年,从未被超越。 本站所有功能及数据仅可用于密码学研究及信息安全评估,";
		
		System.out.println(getSecretKey("abc"));;
		//String password = "keyxyc2014";
		String password = "0D3CED9BEC10A777AEC23CCC353A8C08";
		// 加密
		System.out.println("加密前：" + content);
		String encryptResultStr = encrypt(password, content);
		System.out.println("加密后：" + encryptResultStr);
		// 解密
		System.out.println("解密后：" + decrypt(password, encryptResultStr));
	}
}