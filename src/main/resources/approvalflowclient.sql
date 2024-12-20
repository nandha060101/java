


CREATE TABLE `tb_approval_trans_log` (
  `approvalTransLogId` int(11) NOT NULL AUTO_INCREMENT,
  `transactionId` varchar(100) DEFAULT NULL,
  `applicationCode` varchar(100) DEFAULT NULL,
  `applicationName` varchar(100) DEFAULT NULL,
  `appEvents` varchar(100) DEFAULT NULL,
  `actionItems` varchar(100) DEFAULT NULL,
  `employeeId` varchar(100) DEFAULT NULL,
  `employeeName` varchar(100) DEFAULT NULL,
  `applicationData` JSON DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  `assignedBy` varchar(100) DEFAULT NULL,
  `assignedTo` varchar(100) DEFAULT NULL,
  `createdBy` varchar(100) DEFAULT NULL,
  `createdOn` DATETIME DEFAULT NULL,
  `updatedBy` varchar(100) DEFAULT NULL,
  `updatedOn` DATETIME DEFAULT NULL,
  `isActive` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`approvalTransLogId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

