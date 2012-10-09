public class WordFrequencyPair implements Comparable<Object> {

	private String word = null;
	private Integer count = null;

	public WordFrequencyPair(String s, int c) {
		setWord(s);
		setCount(new Integer(c));
	}

	public WordFrequencyPair(String s, Integer c) {
		setWord(s);
		setCount(c);
	}

	public String getWord() {
		return word;
	}

	public void setWord(String v) {
		this.word = v;
	}

	public Integer getIntegerCount() {
		return count;
	}

	public int getCount() {
		return count.intValue();
	}

	public void setCount(Integer v) {
		this.count = v;
	}

	public int compareTo(Object wfp) {
		return compareTo((WordFrequencyPair) wfp);
	}

	public int compareTo(WordFrequencyPair wfp) {
		return count.compareTo(wfp.getIntegerCount());
	}

}