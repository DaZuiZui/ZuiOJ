    package com.dazuizui.business.util;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.context.annotation.Scope;
    import org.springframework.jdbc.datasource.DataSourceTransactionManager;
    import org.springframework.stereotype.Component;
    import org.springframework.transaction.TransactionDefinition;
    import org.springframework.transaction.TransactionStatus;
    import org.springframework.transaction.support.DefaultTransactionDefinition;

    /**
     * 事物工具类
     */
    @Component
    @Scope("prototype")
    public class TransactionUtils {
        @Autowired
        private DataSourceTransactionManager transactionManager;

        /**
         * 开启事物
         * @param isolationLevel 隔离级别
         * @return
         */
        public TransactionStatus begin(int isolationLevel){
            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            //设置事物隔离
            def.setIsolationLevel(isolationLevel);
            //设置事物传播行为
            def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
            //默认食物
            TransactionStatus transactionStatus = transactionManager.getTransaction(def);

            return transactionStatus;
        }

        /**
         /**
         * 提交事物
         * @param transaction
         */
        public void commit(TransactionStatus transaction) {
            //提交事务
            transactionManager.commit(transaction);
        }

        /**
         * 回滚事物
         * @param transactionStatus
         */
        public void rollback(TransactionStatus transactionStatus){
            transactionManager.rollback(transactionStatus);
        }

    }
