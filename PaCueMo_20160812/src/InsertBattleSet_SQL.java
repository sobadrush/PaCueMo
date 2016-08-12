import java.util.Random;

public class InsertBattleSet_SQL
{

	public static void main(String[] args)
	{
//		String date = "2005-02-29";
//		java.sql.Date javaSqlDate = java.sql.Date.valueOf(date);
//		System.out.println(javaSqlDate);

//		String temp = "2005-02-29";
//		String ss[] = java.sql.Date.valueOf(temp).toString().split("-");
//		System.out.println(ss[1]);
		int count = 0;
		Random rand = new Random();

		for (int i = 10 ; i < 20 ; i++)//今年10月開賽~明年8月
		{
			String year = "2015";
			int month = i;
			if (i > 12)
			{
				month -= 12;
				year = "2016";
			}

			for (int j = 0 ; j < 30 ; j++)//每個月有幾場比賽
			{
				//
				count++;
				//

				int date = 0;
				int hour = rand.nextInt(24);
				int min = rand.nextInt(60);

				String temp = "";
				do
				{
					date = rand.nextInt(30) + 1;
					temp = year + "-" + month + "-" + date;
					String ss[] = java.sql.Date.valueOf(temp).toString().split("-");
//					System.out.println(Integer.valueOf(ss[1]) + "   " + month);

					if (Integer.valueOf(ss[1]) != month)
					{
						continue;
					}
					else
					{
						break;
					}
				}
				while (true);

				//主客隊
				int home = 0;
				int away = 0;
				do
				{
					home = rand.nextInt(30) + 1;
					away = rand.nextInt(30) + 1;
				}
				while (home == away);

				//主客隊得分
				int homeScore = 0;
				int awayScore = 0;
				do
				{
					homeScore = rand.nextInt(60) + 50;
					awayScore = rand.nextInt(60) + 50;
				}
				while (Math.abs(Double.valueOf(homeScore - awayScore)) >= 20.0);

				//主客隊賭金
				int homeBet = 0;
				int awayBet = 0;
				do
				{
					homeBet = (rand.nextInt(50000) + 1) * 100;
					awayBet = (rand.nextInt(50000) + 1) * 100;
				}
				while (Math.abs(Double.valueOf(homeBet - awayBet)) == 0.0);
//				System.out.println((rand.nextInt(50000) + 1) * 100);

//				System.out.println("2015" + String.format("-%02d-%02d %02d:%02d  ,%2d ,%2d ,%3d ,%3d ,%6d ,%6d",
//						month, date, hour, min, home, away, homeScore, awayScore, homeBet, awayBet));
				myformat(year, month, date, hour, min, home, away, homeScore, awayScore, homeBet, awayBet);
				//System.out.println("2015-" + month + "-" + date + " " + hour + ":" + min);
			}
			System.out.println("------------------------------------------");

		}
		System.out.println("-- 共 " + count + " 場比賽 --");
	}

	private static final void myformat(String year, int month, int date, int hour, int min, int homeId, int awayId, int homeScore, int awayScore, int homeBet, int awayBet)
	{
		System.out.println();
		System.out.println("INSERT INTO BattleSet (  battleDateTime , homeId , awayId , homeScore , awayScore , homebet , awaybet )");
		System.out.print("VALUES");
		System.out.println("( '" + String.format("%4s-%02d-%02d %02d:%02d' ,%02d ,%02d ,%3d ,%3d ,%6d ,%6d )", year, month, date, hour, min, homeId, awayId, homeScore, awayScore, homeBet, awayBet));
	}

}
