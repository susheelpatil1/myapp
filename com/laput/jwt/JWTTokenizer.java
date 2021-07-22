package com.laput.jwt;

import java.security.SecureRandom;
import java.util.Date;

import org.springframework.util.StringUtils;

import com.laput.vo.UserInfo;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.*;
import com.nimbusds.jwt.*;

public class JWTTokenizer {
	
	static byte[]  sharedSecret = null;
	static {
		// Generate random 256-bit (32-byte) shared secret
		SecureRandom random = new SecureRandom();
		sharedSecret = new byte[32];
		random.nextBytes(sharedSecret);
	}
	
	public  String generateJWTToken(UserInfo usr){
		String s = null;
		try{
			


	
			// Create HMAC signer
			JWSSigner signer = new MACSigner(sharedSecret);
	
			// Prepare JWT with claims set
			JWTClaimsSet claimsSet = new JWTClaimsSet();
			claimsSet.setSubject("hospital");
			claimsSet.setIssuer("susheel");
			claimsSet.setCustomClaim("usr", usr.getUserId());
			claimsSet.setCustomClaim("role", usr.getRole());

			claimsSet.setExpirationTime(new Date(new Date().getTime() + 60 * 60 * 1000));
	
			SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
	
			// Apply the HMAC protection
			signedJWT.sign(signer);
	
			// Serialize to compact form, produces something like
			// eyJhbGciOiJIUzI1NiJ9.SGVsbG8sIHdvcmxkIQ.onO9Ihudz3WkiauDO2Uhyuz0Y18UASXlSc1eS0NkWyA
			s = signedJWT.serialize();
		}catch(Exception ex){
			
		}
		return s;
	}
	
	
	public boolean validateUser(String token){

		try{
			
			if(StringUtils.isEmpty(token))
				return false;
			
			SignedJWT signedJWT = SignedJWT.parse(token);

			JWSVerifier verifier = new MACVerifier(sharedSecret);
			
			if(signedJWT.verify(verifier)){
	
				// Retrieve / verify the JWT claims according to the app requirements
				if("hospital".equalsIgnoreCase(signedJWT.getJWTClaimsSet().getSubject())
						&& "susheel".equalsIgnoreCase(signedJWT.getJWTClaimsSet().getIssuer())
						&& new Date().before(signedJWT.getJWTClaimsSet().getExpirationTime())){
					System.out.println("user valid");
				}
			}	
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return true;
	}

}

