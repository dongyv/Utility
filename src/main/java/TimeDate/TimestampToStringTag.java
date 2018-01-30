package TimeDate;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 自定义标签（时间戳转字符串）
 * 
 * @author Administrator
 * 
 */
public class TimestampToStringTag extends TagSupport{
	private static final long serialVersionUID = 1L;

	private String value;// 值
	private String pattern;// 格式

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public int doStartTag() throws JspException {
		if ((value == null) || (value.equals(""))) {
			try {
				pageContext.getOut().write("");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return super.doStartTag();
		}
		String vv = String.valueOf(value);
		long time = 0L;
		if (vv.length() < 11)
			time = Long.valueOf(vv).longValue() * 1000L;
		else {
			time = Long.valueOf(vv).longValue();
		}

		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(time);
		SimpleDateFormat dateformat = new SimpleDateFormat(pattern);
		String s = dateformat.format(c.getTime());
		try {
			pageContext.getOut().write(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.doStartTag();
	}
}
