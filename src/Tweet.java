import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Tweet implements Comparable<Tweet> {
	String Text;
	String Date;

	public Tweet(String Text, String Date) {
		this.Text = Text;
		this.Date = Date;
	}

	public String toString() {
		return "TWEET: " + this.Text + ":DATE: " + this.Date;
	}

	private java.util.Date StringDateToDate(String OldDate)
			throws ParseException {

		final String TWITTER = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
		SimpleDateFormat sf = new SimpleDateFormat(TWITTER, Locale.ENGLISH);
		sf.setLenient(true);
		return sf.parse(OldDate);

	}

	@Override
	public int compareTo(Tweet arg0) {
		// TODO Auto-generated method stub

		java.util.Date thisDate = null;
		java.util.Date oDate = null;
		try {
			thisDate = StringDateToDate(Date);
			oDate = StringDateToDate(arg0.Date);

			if (this.Date == null) {
				return 1;
			}
			if (arg0.Date == null) {
				return -1;
			}

			return thisDate.compareTo(oDate);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}
