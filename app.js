const Web3 = require('web3');
const web3 = new Web3('http://localhost:8090/rpc');

async function getChainHeight() {
    const height = await web3.eth.getBlockNumber();
    console.log('Chain Height:', height);
    return height;
}

async function sendTransaction(from, to, value) {
    const tx = {
        from: from,
        to: to,
        value: web3.utils.toWei(value, 'ether'),
        gas: 21000
    };
    const receipt = await web3.eth.sendTransaction(tx);
    console.log('Transaction Hash:', receipt.transactionHash);
    return receipt;
}

async function getBlockByNumber(height) {
    const block = await web3.eth.getBlock(height);
    console.log('Block Data:', block);
    return block;
}

module.exports = { getChainHeight, sendTransaction, getBlockByNumber };
