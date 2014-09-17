package io;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class HTMLDownloader {

	private URL url;
	private InputStream is = null;
	private BufferedReader br = null;
	private String htmlCode = null;

	public HTMLDownloader(String url) throws IOException {
		this.url = new URL(url);
		is = this.url.openStream();
		br = new BufferedReader(new InputStreamReader(is));
	}

	public String getHTML() throws IOException {

		if (htmlCode != null) {
			return htmlCode;
		} else {
			String line = null;
			StringBuilder sb = new StringBuilder();

			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

			htmlCode = sb.toString();
			return htmlCode;
		}
	}
}
