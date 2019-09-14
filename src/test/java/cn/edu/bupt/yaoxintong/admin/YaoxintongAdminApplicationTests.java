package cn.edu.bupt.yaoxintong.admin;

import java.util.Date;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.edu.bupt.yaoxintong.admin.componet.PasswordHelper;
import cn.edu.bupt.yaoxintong.admin.componet.PasswordHelper.PasswordAndSalt;
import cn.edu.bupt.yaoxintong.admin.pojo.YaoxintongAdmin;
import cn.edu.bupt.yaoxintong.admin.repository.AdminRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class YaoxintongAdminApplicationTests {

	
	@Autowired
	AdminRepository adminRepository;
	@Autowired
	PasswordHelper passwordHelper;
	
	@Test
	public void regTest() {
		YaoxintongAdmin admin = new YaoxintongAdmin();
		String userId = UUID.randomUUID().toString();
		admin.setId(userId);
		admin.setAdminname("test");
		PasswordAndSalt encryptPassword = passwordHelper.encryptPassword("123");
		admin.setPassword(encryptPassword.getPassword());// 密码加密
		admin.setSalt(encryptPassword.getSalt());
		admin.setRegistertime(new Date());
		admin.setType("1");
		adminRepository.saveAndFlush(admin);
	}
	
	@Test
	public void delTest() {
		
		adminRepository.delete("5144816c-8f7c-4995-9490-b0f72edb8610");
	}

}
