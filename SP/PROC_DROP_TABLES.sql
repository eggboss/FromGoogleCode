USE EZactor751_1999_backup_0304;
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Kirk Hsu
-- Create date: 2010/05/08
-- Description:	一次刪除多個資料表(prefix0_~prefix9_5)
-- Param:       @prefix
-- Param:       @start
-- Param:       @end
-- =============================================
CREATE PROCEDURE PROC_DROP_TABLES
(
	@prefix nvarchar(50),
	@start int,
	@end int
)
AS
BEGIN
	SET NOCOUNT ON;
	-- 宣告變數
	DECLARE @count1 INT, @count2 INT, @tab nvarchar(50), @command nvarchar(200), @tmp nvarchar(50)
	SET @count1 = @start
	SET @count2 = 0

	WHILE (@count1 <= @end) 
	BEGIN
		SET @tmp = @prefix + convert(nvarchar(10),@count1);
		--print @tmp+'_0'
		IF @count2=0 AND OBJECT_ID ( @tmp+'_0', 'U' ) IS NULL 
			BREAK;
		ELSE
			WHILE (@count2 < 10) 
			BEGIN
				SET @tab = @prefix + convert(nvarchar(10),@count1) + '_' + convert(nvarchar(2),@count2)
				--print @tab
				
				-- 若表格不存在就跳出
				IF OBJECT_ID ( @tab, 'U' ) IS NULL 
					BREAK;
				ELSE
					-- 刪除表格Command
					SET @command = N'drop TABLE ' + @tab;
					print @command + N' OK !'

					execute(@command);

					SET @count2 = (@count2 + 1);
			END

			SET @count1 = (@count1 + 1);
			SET @count2 = 0;
	END
END
GO
