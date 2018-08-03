package com.redis.cache.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redis.cache.model.Customer;
import com.redis.cache.repo.CustomerRepository;

@Service
public class RedisService {

	@Autowired
	private CustomerRepository customerRepository;

	public static String encodeToString(BufferedImage image, String type) {
		String imageString = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		try {
			ImageIO.write(image, type, bos);
			byte[] imageBytes = bos.toByteArray();

			Base64.Encoder mimeEncoder = Base64.getMimeEncoder();
			imageString = mimeEncoder.encodeToString(imageBytes);

			bos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return imageString;
	}

	public void getCustomer(Customer customer) {
		try {
			File file = new File("/home/bridgeit/demo.png");
			BufferedImage image = ImageIO.read(file);
			String base64 = encodeToString(image, "png");
			customer.setProfile(base64);
			customerRepository.save(customer);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
