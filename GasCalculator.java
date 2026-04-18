package com.nexusledger.fees;

import com.nexusledger.transaction.Transaction;

public class GasCalculator {
    private static final double BASE_GAS = 21000.0;
    private static final double DATA_GAS_PER_BYTE = 68.0;
    private double gasPrice;

    public GasCalculator(double price) {
        this.gasPrice = price;
    }

    public double calculateTransactionGas(Transaction tx) {
        int dataSize = tx.toString().getBytes().length;
        return BASE_GAS + (dataSize * DATA_GAS_PER_BYTE);
    }

    public double calculateTransactionFee(Transaction tx) {
        return calculateTransactionGas(tx) * gasPrice;
    }

    public void adjustGasPrice(double newPrice) {
        this.gasPrice = newPrice;
    }

    public double getGasPrice() {
        return gasPrice;
    }
}
