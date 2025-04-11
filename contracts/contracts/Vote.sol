// SPDX-License-Identifier: UNLICENSED
pragma solidity ^0.8.28;

contract Vote {
    struct Candidate {
        string name;
        uint voteCount;
    }

    address public admin;
    string public title;
    string public description;
    uint public deadline;

    Candidate[] public candidates;
    mapping(address => bool) public hasVoted;

    constructor(
        string memory _title,
        string memory _description,
        uint _durationMinutes
    ) {
        admin = msg.sender;
        title = _title;
        description = _description;
        deadline = block.timestamp + (_durationMinutes * 1 minutes);
    }

    function addCandidate(string memory _name) public {
        require(msg.sender == admin, unicode"후보는 관리자만 추가가 가능합니다.");
        candidates.push(Candidate(_name, 0));
    }

    function vote(uint _candidateIndex) public {
        require(block.timestamp < deadline, unicode"투표가 종료됐습니다.");
        require(!hasVoted[msg.sender], unicode"이미 투표하셨습니다.");
        require(_candidateIndex < candidates.length, unicode"후보의 정보를 알 수 없습니다.");

        candidates[_candidateIndex].voteCount += 1;
        hasVoted[msg.sender] = true;
    }

    function getCandidates() public view returns (Candidate[] memory) {
        return candidates;
    }

    function getWinner() public view returns (string memory winnerName) {
        require(block.timestamp >= deadline, unicode"투표가 진행중입니다.");

        uint maxVotes = 0;
        uint winnerIndex;

        for(uint i=0; i<candidates.length; i++) {
            if(candidates[i].voteCount > maxVotes) {
                maxVotes = candidates[i].voteCount;
                winnerIndex = i;
            }
        }

        winnerName = candidates[winnerIndex].name;
    }
}