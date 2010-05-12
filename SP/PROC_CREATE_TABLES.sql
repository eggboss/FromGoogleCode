USE EZactor751_1999_backup_0304;
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Kirk Hsu
-- Create date: 2010/05/08
-- Description:	一次建立多個測試用表格(prefix1_0~prefix9_9)
-- Param:       @prefox
-- Param:       @max
-- =============================================
CREATE PROCEDURE PROC_CREATE_TABLES
(
	@prefox nvarchar(50),
	@max int
)
AS
BEGIN
	SET NOCOUNT ON;

	DECLARE @count1 INT,@count2 INT, @tab nvarchar(50), @command nvarchar(200)
	SET @count1 = 1
	SET @count2 = 0
	WHILE (@count1 <= @max) 
	BEGIN
		WHILE (@count2 < 10)
		BEGIN
			SET @tab = @prefox + convert(nvarchar(5),@count1) + '_' + convert(nvarchar(5),@count2)
			--print @tab
			
			-- 建立表格Command
			SET @command = N'CREATE TABLE ' + @tab + N'( date_id int )';
			-- 刪除表格Command
			--SET @command = N'drop TABLE ' + @tab;
			print @command

			execute(@command);
			
			SET @count2 = (@count2 + 1);
		END
		
		SET @count1 = (@count1 + 1);
		SET @count2 = 0;
	END
END
GO
