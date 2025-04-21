package contract;

import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.tx.gas.DefaultGasProvider;

@Service
public class ContractVoteService {
	
	private final Web3j web3j;
	private final Credentials credentials;
	private Vote voteContract;
	
	public ContractVoteService(Web3j web3j, Credentials credentials) {
		this.web3j = web3j;
		this.credentials = credentials;
		String contractAddress = "";
		this.voteContract = Vote.load(contractAddress, web3j, credentials, new DefaultGasProvider());
	}
	
	public void addCandidate(String name) {
		try {
			voteContract.addCandidate(name).send();
		} catch (Exception e) {
			throw new RuntimeException("Failed to add candidate to blockchain", e);
		}
	}
}
