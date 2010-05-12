USE EZactor751_1999_backup_0304;
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Kirk Hsu
-- Create date: 2010/05/11
-- Description:	將Contact相關的五個資料表的資料搬移到history資料表中，
--              設計為一天一天搬(一天為一個Transaction)
-- Param:       @daycount 備份幾天前的資料
-- =============================================
CREATE PROCEDURE MOVE_DATA_TO_HISTORY_TABLE 
(
	@daycount INT
)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

	-- 若資料表不存在，則新建history資料表
	IF OBJECT_ID ( 'dbo.tb000000Contact_history', 'U' ) IS NULL
		CREATE TABLE [dbo].[tb000000Contact_history](
			[lId] [bigint] IDENTITY(101,1) NOT NULL,
			[nBoundType] [int] NOT NULL,
			[nResult] [int] NOT NULL DEFAULT ((0)),
			[lMainCommId] [bigint] NOT NULL DEFAULT ((0)),
			[lActivityId] [bigint] NOT NULL DEFAULT ((0)),
			[lCustomerId] [bigint] NOT NULL DEFAULT ((0)),
			[strCustomerName] [nvarchar](64) COLLATE Chinese_Taiwan_Stroke_CI_AS NOT NULL DEFAULT (' '),
			[dtStartTime] [datetime] NOT NULL DEFAULT (getdate()),
			[dtEndTime] [datetime] NULL,
			[strMemo] [ntext] COLLATE Chinese_Taiwan_Stroke_CI_AS NOT NULL DEFAULT (' '),
			[dtCreateTime] [datetime] NOT NULL DEFAULT (getdate()),
			[lCreatorId] [bigint] NOT NULL DEFAULT ((0)),
			[strCreatorName] [nvarchar](64) COLLATE Chinese_Taiwan_Stroke_CI_AS NOT NULL DEFAULT (' '),
			[dtModifyTime] [datetime] NOT NULL DEFAULT (getdate()),
			[lModifierId] [bigint] NOT NULL DEFAULT ((0)),
			[strModifierName] [nvarchar](64) COLLATE Chinese_Taiwan_Stroke_CI_AS NOT NULL DEFAULT (' '),
			[lContacteeId] [bigint] NOT NULL DEFAULT ((0)),
			[lQuestionnaireId] [bigint] NULL DEFAULT ((0)),
		PRIMARY KEY CLUSTERED 
		(
			[lId] ASC
		)WITH (PAD_INDEX  = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]
		) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

	IF OBJECT_ID ( 'dbo.tb000000ContactBusiness_history', 'U' ) IS NULL
		CREATE TABLE [dbo].[tb000000ContactBusiness_history](
			[lContactId] [bigint] NOT NULL,
			[lBusinessId] [bigint] NOT NULL,
		PRIMARY KEY CLUSTERED 
		(
			[lContactId] ASC,
			[lBusinessId] ASC
		)WITH (PAD_INDEX  = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]
		) ON [PRIMARY]

	IF OBJECT_ID ( 'dbo.tb000000ContactComm_history', 'U' ) IS NULL
		CREATE TABLE [dbo].[tb000000ContactComm_history](
			[lId] [bigint] IDENTITY(101,1) NOT NULL,
			[lContactId] [bigint] NOT NULL,
			[nChannelId] [int] NOT NULL,
			[strChannelValue] [nvarchar](1600) COLLATE Chinese_Taiwan_Stroke_CI_AS NOT NULL DEFAULT (' '),
			[strInteractionId] [nvarchar](32) COLLATE Chinese_Taiwan_Stroke_CI_AS NOT NULL DEFAULT (' '),
			[strContent] [ntext] COLLATE Chinese_Taiwan_Stroke_CI_AS NOT NULL DEFAULT (' '),
			[strAttachedDataXML] [ntext] COLLATE Chinese_Taiwan_Stroke_CI_AS NOT NULL DEFAULT (' '),
			[lAgentCTIId] [bigint] NOT NULL,
			[dtStartTime] [datetime] NOT NULL DEFAULT (getdate()),
			[dtEndTime] [datetime] NULL,
		PRIMARY KEY CLUSTERED 
		(
			[lId] ASC
		)WITH (PAD_INDEX  = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]
		) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

	IF OBJECT_ID ( 'dbo.tb000000ContactFAQ_history', 'U' ) IS NULL
		CREATE TABLE [dbo].[tb000000ContactFAQ_history](
			[lContactId] [bigint] NOT NULL,
			[lFAQId] [bigint] NOT NULL,
		PRIMARY KEY CLUSTERED 
		(
			[lContactId] ASC,
			[lFAQId] ASC
		)WITH (PAD_INDEX  = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]
		) ON [PRIMARY]

	IF OBJECT_ID ( 'dbo.tb000000ContactSummary_history', 'U' ) IS NULL
		CREATE TABLE [dbo].[tb000000ContactSummary_history](
			[lContactId] [bigint] NOT NULL,
			[lSummaryId] [bigint] NOT NULL,
		PRIMARY KEY CLUSTERED 
		(
			[lContactId] ASC,
			[lSummaryId] ASC
		)WITH (PAD_INDEX  = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]
		) ON [PRIMARY]

----------------------------- 搬資料	

	DECLARE 
		@max INT, -- 距離今天最遠的天數
		@current INT -- 目前的指標

	-- 取得距離今天最遠的天數
	SELECT @max=MAX(datediff(dd,t.dtCreateTime, getdate())) FROM tb000000Contact t; 

	SET @current = @max;

	WHILE (@current > @daycount)
	BEGIN	
		BEGIN TRANSACTION;

		BEGIN TRY

			-- 先將本次將處理的資料搬到暫存資料表，最後會再刪除掉！減少後面的查詢次數！
			SELECT * INTO dbo.tb000000Contact_temp FROM dbo.tb000000Contact WHERE datediff(dd,[dbo].[tb000000Contact].dtCreateTime, getdate()) >= @current;

			-- tb000000Contact_history
			SET IDENTITY_INSERT [dbo].[tb000000Contact_history] ON;
			INSERT INTO [dbo].[tb000000Contact_history]([lId], [nBoundType], [nResult], [lMainCommId], [lActivityId], [lCustomerId], [strCustomerName], [dtStartTime], [dtEndTime], [strMemo], [dtCreateTime], [lCreatorId], [strCreatorName], [dtModifyTime], [lModifierId], [strModifierName], [lContacteeId], [lQuestionnaireId]) 
				SELECT [lId], [nBoundType], [nResult], [lMainCommId], [lActivityId], [lCustomerId], [strCustomerName], [dtStartTime], [dtEndTime], [strMemo], [dtCreateTime], [lCreatorId], [strCreatorName], [dtModifyTime], [lModifierId], [strModifierName], [lContacteeId], [lQuestionnaireId] 
				FROM [dbo].[tb000000Contact_temp] 
			SET IDENTITY_INSERT [dbo].[tb000000Contact_history] OFF;

			-- tb000000ContactBusiness_history
			INSERT INTO [dbo].[tb000000ContactBusiness_history]([lContactId], [lBusinessId]) 
				SELECT [lContactId], [lBusinessId] 
				FROM [dbo].[tb000000ContactBusiness]
				WHERE exists (select h.lId from dbo.tb000000Contact_temp h where h.lid=lContactId);

			-- tb000000ContactComm_history
			SET IDENTITY_INSERT [dbo].[tb000000ContactComm_history] ON;
			INSERT INTO [dbo].[tb000000ContactComm_history]
						([lId],[lContactId],[nChannelId],[strChannelValue],[strInteractionId],[strContent],[strAttachedDataXML],[lAgentCTIId],[dtStartTime],[dtEndTime])
				SELECT  [lId],[lContactId],[nChannelId],[strChannelValue],[strInteractionId],[strContent],[strAttachedDataXML],[lAgentCTIId],[dtStartTime],[dtEndTime]
				FROM [dbo].[tb000000ContactComm]
				WHERE exists (select h.lId from dbo.tb000000Contact_temp h where h.lid=lContactId);
			SET IDENTITY_INSERT [dbo].[tb000000ContactComm_history] OFF;

			-- tb000000ContactFAQ_history
			INSERT INTO [dbo].[tb000000ContactFAQ_history]([lContactId], [lFAQId]) 
				SELECT [lContactId], [lFAQId] 
				FROM [dbo].[tb000000ContactFAQ]
				WHERE exists (select * from dbo.tb000000Contact_temp h where h.lid=lContactId);

			-- tb000000ContactSummary_history
			INSERT INTO [dbo].[tb000000ContactSummary_history]([lContactId], [lSummaryId]) 
				SELECT [lContactId], [lSummaryId] 
				FROM [dbo].[tb000000ContactSummary]
				WHERE exists (select h.lId from dbo.tb000000Contact_temp h where h.lid=lContactId);
			
			-- 有參考到的資料會一起被刪除
			DELETE FROM [dbo].[tb000000Contact] WHERE datediff(dd, [dbo].[tb000000Contact].dtCreateTime, getdate()) >= @current;
			
			-- 移除暫存資料表
			DROP TABLE dbo.tb000000Contact_temp;

			COMMIT TRANSACTION;
			PRINT N'成功搬移' + convert(nvarchar(10),@current) + N'天前的資料！';

		END TRY
		BEGIN CATCH 
			PRINT ERROR_MESSAGE();
			ROLLBACK TRANSACTION;
			PRINT N'錯誤發生，資料ROLLBACK!!';
			BREAK;
		END CATCH 
		
		SET @current = (@current-1);
	END	

END
GO
