     USE [Test]
        GO
        
        /****** Object:  Table [dbo].[student]    Script Date: 9/13/2021 12:07:13 PM ******/
        SET ANSI_NULLS ON
        GO
        
        SET QUOTED_IDENTIFIER ON
        GO
        
        CREATE TABLE [dbo].[student](
        	[student_id] [int] NULL,
        	[student_name] [nchar](1000) NULL,
        	[student_age] [int] NULL,
        	[primaryKey] [bigint] IDENTITY(1,1) NOT NULL,
        	[version] [bigint] NOT NULL,
         CONSTRAINT [PK_persionId] PRIMARY KEY NONCLUSTERED 
        (
        	[primaryKey] ASC
        )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
        ) ON [PRIMARY]
        GO
    
    CREATE TABLE [dbo].[student_History](
    	[student_id] [int] NULL,
    	[student_name] [nchar](1000) NULL,
    	[student_age] [int] NULL,
    	[primaryKey] [bigint] NOT NULL,
    	[version] [bigint] NOT NULL,
    	[revision] [int] NOT NULL,
    	[revisionType] [tinyint] NULL
    ) ON [PRIMARY]
    GO
    
    CREATE TABLE [dbo].[REVINFO](
    	[id] [int] NULL,
    	[REVTSTMP] [bigint] NOT NULL
    ) ON [PRIMARY]
    GO