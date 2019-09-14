package cn.edu.bupt.yaoxintong.admin.componet;


import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

@Component
public class PasswordHelper {

    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

//    @Value("${password.algorithmName}")
    private String algorithmName = "md5";
//    @Value("${password.hashIterations}")
    private int hashIterations = 2;

    public void setRandomNumberGenerator(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public void setHashIterations(int hashIterations) {
        this.hashIterations = hashIterations;
    }

    
    public PasswordAndSalt  encryptPassword(String password) {
        String salt = randomNumberGenerator.nextBytes().toHex();
		String newStr = new SimpleHash(
                algorithmName,
                password,
                ByteSource.Util.bytes(password+salt),
                hashIterations).toHex();

        PasswordAndSalt passwordAndSalt = new PasswordAndSalt();
        passwordAndSalt.setPassword(newStr);
        passwordAndSalt.setSalt(salt);
		return passwordAndSalt;
    }
    
    public String  encryptPassword(String password,String salt) {
		String newStr = new SimpleHash(
                algorithmName,
                password,
                ByteSource.Util.bytes(password+salt),
                hashIterations).toHex();
		return newStr;
    }
    
    public class PasswordAndSalt{
    	private String password;
    	private String salt;
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getSalt() {
			return salt;
		}
		public void setSalt(String salt) {
			this.salt = salt;
		}

    }
}
