USE [SCHOOL]
GO
/****** Object:  Table [dbo].[Course]    Script Date: 1/17/2017 7:40:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Course](
	[code] [char](10) NOT NULL,
	[title] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[code] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Registered]    Script Date: 1/17/2017 7:40:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Registered](
	[ssn] [numeric](18, 0) NOT NULL,
	[code] [char](10) NOT NULL,
	[year] [int] NOT NULL,
	[semester] [char](10) NOT NULL,
	[grade] [char](1) NULL,
PRIMARY KEY CLUSTERED 
(
	[ssn] ASC,
	[code] ASC,
	[year] ASC,
	[semester] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Student]    Script Date: 1/17/2017 7:40:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Student](
	[ssn] [numeric](18, 0) NOT NULL,
	[name] [char](50) NULL,
	[address] [varchar](100) NULL,
	[major] [char](10) NULL,
PRIMARY KEY CLUSTERED 
(
	[ssn] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
ALTER TABLE [dbo].[Registered]  WITH CHECK ADD FOREIGN KEY([code])
REFERENCES [dbo].[Course] ([code])
GO
ALTER TABLE [dbo].[Registered]  WITH CHECK ADD FOREIGN KEY([ssn])
REFERENCES [dbo].[Student] ([ssn])
GO
