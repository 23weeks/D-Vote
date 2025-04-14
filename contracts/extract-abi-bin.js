const fs = require('fs');
const path = require('path');

const contractName = 'Vote';
const inputPath = path.join(__dirname, 'artifacts', 'contracts', `${contractName}.sol`, `${contractName}.json`);
const outputDir = path.join(__dirname, 'build');

if (!fs.existsSync(outputDir)) {
    console.error(`Cannot find file at ${inputPath}`);
    fs.mkdirSync(outputDir);
}

const json = JSON.parse(fs.readFileSync(inputPath, 'utf8'));
const abi = JSON.stringify(json.abi, null, 2);
const bytecode = json.bytecode;

fs.writeFileSync(path.join(outputDir, `${contractName}.abi`), abi);
fs.writeFileSync(path.join(outputDir, `${contractName}.bin`), bytecode);

console.log(`>>> ${contractName}.abi and ${contractName}.bin create in /build`);