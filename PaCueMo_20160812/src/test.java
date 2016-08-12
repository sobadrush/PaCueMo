import com.google.gson.Gson;

public class test
{
	private int id = 123;
	private String name = "比卡超";
	private Integer age = 18;

	public static void main(String[] args)
	{
//		java.sql.Date name = new java.sql.Date(System.currentTimeMillis());
//		System.out.println(name);
//---------
//		java.sql.Date dd = new java.sql.Date(System.currentTimeMillis());
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		String formatDate = sdf.format(dd);
//		System.out.println(formatDate);
//---------		
		Gson gson = new Gson();
		String ss = gson.toJson(new test());
		System.out.println(ss);
	}

}
