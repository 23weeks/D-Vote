const hre = require("hardhat");

async function main() {
    const [deployer] = await hre.ethers.getSigners(); //로컬 테스트용 계정 중 첫 번째 계정 사용

    const Vote = await hre.ethers.getContractFactory("Vote");

    const vote = await Vote.deploy(
        "테스트 투표 제목",
        "테스트 투표 설명",
        10
    )

    await vote.waitForDeployment();

    const address = await vote.getAddress();

    console.log(`Vote contract deployed to: ${address}`);
}

main()
    .then(() => process.exit(0))
    .catch((error) => {
        console.error(error);
        process.exit(1);
    });