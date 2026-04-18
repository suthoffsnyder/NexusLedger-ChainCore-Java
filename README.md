# NexusLedger-ChainCore-Java
企业级区块链底层核心框架，基于Java构建，集成分布式账本、共识算法、加密签名、智能合约、跨链交互、节点通信、数据上链、隐私计算等全栈功能，支持多语言扩展，适用于联盟链、公链、供应链金融、数字资产等区块链场景落地。

## 项目特性
- 支持PoW/PoS混合共识机制
- 原生实现ECDSA签名、SHA-256哈希、默克尔树、零知识证明
- 完整的交易池、区块生成、节点P2P通信
- 智能合约引擎（支持Token/NFT合约）
- 跨链桥、数据持久化、链同步、链监控
- 多签钱包、Gas费模型、分片管理、链上升级
- 提供Web3 RPC接口、HTTP API服务
- 支持JavaScript/YAML多语言扩展

## 文件清单与功能说明
### 核心账本模块
1. **BlockchainCore.java** - 区块链主核心类，管理链结构、区块添加、链有效性验证
2. **Block.java** - 区块数据结构，实现哈希计算、挖矿逻辑
3. **GenesisBlockGenerator.java** - 创世区块生成与校验工具

### 共识算法模块
4. **ConsensusPoW.java** - 工作量证明共识实现，挖矿校验、最长链选择
5. **ConsensusPoS.java** - 权益证明共识实现，质押、验证者选举
6. **ValidatorManager.java** - 验证者管理，注册、封禁、解禁验证节点

### 加密安全模块
7. **CryptoSignature.java** - ECDSA非对称签名/验签、密钥生成
8. **HashUtil.java** - SHA256/MD5哈希、默克尔根计算工具
9. **MerkleTree.java** - 默克尔树构建，交易数据完整性校验
10. **ZeroKnowledgeProof.java** - 零知识证明，隐私数据验证

### 交易与钱包模块
11. **Transaction.java** - 交易实体，签名、有效性校验
12. **TransactionPool.java** - 待打包交易池管理
13. **Wallet.java** - 标准钱包，地址生成、数据签名
14. **MultiSignatureWallet.java** - 多签钱包，多节点签名授权
15. **TransactionApproval.java** - 多签交易审批状态管理

### P2P网络模块
16. **P2PNode.java** - P2P节点服务，节点发现、消息广播
17. **P2PMessageHandler.java** - P2P消息解析与分发

### 智能合约模块
18. **SmartContractBase.java** - 智能合约抽象基类
19. **TokenContract.java** - 同质化代币合约
20. **NFTContract.java** - 非同质化代币(NFT)合约
21. **ContractExecutor.java** - 合约部署、执行、销毁引擎

### 数据存储模块
22. **ChainDataStorage.java** - 区块链数据JSON持久化与备份
23. **LevelDBAdapter.java** - LevelDB键值存储适配器

### 跨链与分片模块
24. **CrossChainBridge.java** - 跨链资产转移桥
25. **ShardingManager.java** - 区块链分片管理，负载均衡

### 费用与激励模块
26. **GasCalculator.java** - Gas消耗与交易手续费计算
27. **FeeDistributor.java** - 手续费分配，矿工/国库收益

### 服务与接口模块
28. **ChainAPIService.java** - 区块链HTTP API服务
29. **Web3RPCServer.java** - Web3兼容RPC服务
30. **RPCHandler.java** - RPC请求处理器

### 监控与同步模块
31. **ChainMonitor.java** - 链健康度、TPS、区块高度监控
32. **ChainSyncService.java** - 节点间链数据同步

### 挖矿与升级模块
33. **MinerNode.java** - 挖矿节点，打包交易生成区块
34. **ChainUpgradeManager.java** - 链协议无缝升级

### 多语言扩展
35. **app.js** - JavaScript Web3交互工具
36. **config.yaml** - YAML节点配置文件

### 配置模块
37. **NodeConfiguration.java** - 节点参数配置管理

## 技术栈
- 主语言：Java 11+
- 加密：SHA-256、ECDSA、AES-256
- 存储：LevelDB、文件持久化
- 网络：NIO、HTTP、P2P、Web3 RPC
- 多语言：JavaScript、YAML
- 架构：模块化、微内核、可扩展

## 适用场景
- 联盟链/私有链部署
- 数字资产发行与管理
- 供应链金融上链
- NFT发行与交易
- 跨链资产互通
- 去中心化身份(DID)
- 链上数据存证
