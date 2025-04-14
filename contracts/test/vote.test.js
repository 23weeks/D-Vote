const { expect } = require("chai");
const { ethers } = require("hardhat");

describe("Vote contract", function () {
    if("Should set the right title and description", async function () {
        const Vote = await ethers.getContractFactory("Vote");
        const vote = await Vote.deploy("Test Title", "TestDescription", 10);
        await vote.waitForDeployment();

        expect(await vote.title()).to.equal("Test Title");
        expect(await vote.description()).to.equal("Test Description");
    });

    it("Should add a candidate and vote", async function () {
        const [owner, voter] = await ethers.getSigners();
        const Vote = await ethers.getContractFactory("Vote");
        const vote = await Vote.deploy("Test", "Desc", 10);
        await vote.waitForDeployment();

        await vote.addCandidate("Candidate 1");
        await vote.connect(voter).vote(0);

        const candidates = await vote.getCandidates();
        expect(candidates[0].voteCount).to.equal(1);
    });
});