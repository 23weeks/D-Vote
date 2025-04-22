package contract;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

@Configuration
public class Web3jConfig {

	@Value("${ethereum.private-key}")
	private String privateKey;
	
	@Value("${ethereum.contract-address}")
	private String contractAddress;
	
	@Value("${ethereum.private-node-url}")
	private String nodeUrl;
	
	@Bean
	public Web3j web3j() {
		return Web3j.build(new HttpService(nodeUrl));
	}
	
	@Bean
	public Credentials credentials() {
		return Credentials.create(privateKey);
	}
	
	@Bean
	public String contractAddress() {
		return contractAddress;
	}
}
