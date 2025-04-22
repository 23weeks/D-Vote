package contract;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.gas.DefaultGasProvider;

import com.dvote.backend.Exception.CustomException;

@Service
public class ContractVoteService {
	
	private final Vote voteContract;
	
	public ContractVoteService(Web3j web3j, Credentials credentials, String contractAddress) {
		this.voteContract = Vote.load(contractAddress, web3j, credentials, new DefaultGasProvider());
	}
	
	public Vote getVoteContract() {
		return voteContract;
	}
	
	//후보 등록
	public void addCandidate(String name) {
		try {
			TransactionReceipt receipt = voteContract.addCandidate(name).send();
			System.out.println(">>> Transaction hash: " + receipt.getTransactionHash());
		} catch (Exception e) {
			throw new CustomException("블록체인에 후보자 등록 실패", 401);
		}
	}
	
	//투표 메서드
	public void vote(int candidateIndex) {
		try {
			TransactionReceipt receipt = voteContract.vote(BigInteger.valueOf(candidateIndex)).send();
			System.out.println("Voted successfully. Tx: " + receipt.getTransactionHash());
		} catch (Exception e) {
			throw new CustomException("투표 실패", 401);
		}
	}
	
	//후보 목록 조회
	public List<Vote.Candidate> getCandidates() {
		try {
			return voteContract.getCandidates().send();
		} catch (Exception e) {
			throw new CustomException("후보 목록 조회 실패", 401);
		}
	}
	
	//승자 조회
	public String getWinner() {
		try {
			return voteContract.getWinner().send();
		} catch (Exception e) {
			throw new CustomException("승자 조회 실패", 401);
		}
	}
	
	//중복 투표 확인
	public boolean hasVoted(String address) {
		try {
			return voteContract.hasVoted(address).send();
		} catch (Exception e) {
			throw new CustomException("중복 투표 확인 실패", 401);
		}
	}
}
