package org.jsoup.parser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Case	Input Char	Input String	Unordered input Char Array	Ordered input Char Array	Ignore Case	Position	Content	Should Match	Notes
// 4.1	Lowercase Letters	N/A	N/A	N/A	FALSE	normal	multi chars	FALSE	Base Case with Input Char chosen
// 4.2	Uppercase Letters	N/A	N/A	N/A	FALSE	normal	multi chars	FALSE	Varying Input Char with Input Char chosen
// 4.3	Number	N/A	N/A	N/A	FALSE	normal	multi chars	FALSE	Varying Input Char with Input Char chosen
// 4.4	Space	N/A	N/A	N/A	FALSE	normal	multi chars	FALSE	Varying Input Char with Input Char chosen
// 4.5	Punctuation	N/A	N/A	N/A	FALSE	normal	multi chars	FALSE	Varying Input Char with Input Char chosen
// 4.6	Lowercase Letters	N/A	N/A	N/A	FALSE	0	multi chars	FALSE	Varying Position with Input Char chosen
// 4.7	Lowercase Letters	N/A	N/A	N/A	FALSE	1	multi chars	FALSE	Varying Position with Input Char chosen
// 4.8	Lowercase Letters	N/A	N/A	N/A	FALSE	second last	multi chars	FALSE	Varying Position with Input Char chosen
// 4.9	Lowercase Letters	N/A	N/A	N/A	FALSE	last	multi chars	FALSE	Varying Position with Input Char chosen
// 4.10	Lowercase Letters	N/A	N/A	N/A	FALSE	finished	multi chars	FALSE	Varying Position with Input Char chosen
// 4.11	Lowercase Letters	N/A	N/A	N/A	FALSE	normal	Empty	FALSE	Varying Content with Input Char chosen
// 4.12	Lowercase Letters	N/A	N/A	N/A	FALSE	normal	one char	FALSE	Varying Content with Input Char chosen
// 4.13	Lowercase Letters	N/A	N/A	N/A	FALSE	normal	super long	FALSE	Varying Content with Input Char chosen
// 4.14	Lowercase Letters	N/A	N/A	N/A	FALSE	normal	multi chars	TRUE	Varying Should Match with Input Char chosen
// 4.15	N/A	normal	N/A	N/A	FALSE	normal	multi chars	FALSE	Base Case with Input String chosen
// 4.16	N/A	""(Empty)	N/A	N/A	FALSE	normal	multi chars	FALSE	Varying Input String with Input String chosen
// 4.17	N/A	short	N/A	N/A	FALSE	normal	multi chars	FALSE	Varying Input String with Input String chosen
// 4.18	N/A	super long	N/A	N/A	FALSE	normal	multi chars	FALSE	Varying Input String with Input String chosen
// 4.19	N/A	normal	N/A	N/A	TRUE	normal	multi chars	FALSE	Varying Ignore Case with Input String chosen
// 4.20	N/A	normal	N/A	N/A	FALSE	0	multi chars	FALSE	Varying Position with Input String chosen
// 4.21	N/A	normal	N/A	N/A	FALSE	1	multi chars	FALSE	Varying Position with Input String chosen
// 4.22	N/A	normal	N/A	N/A	FALSE	second last	multi chars	FALSE	Varying Position with Input String chosen
// 4.23	N/A	normal	N/A	N/A	FALSE	last	multi chars	FALSE	Varying Position with Input String chosen
// 4.24	N/A	normal	N/A	N/A	FALSE	finished	multi chars	FALSE	Varying Position with Input String chosen
// 4.25	N/A	normal	N/A	N/A	FALSE	normal	Empty	FALSE	Varying Content with Input String chosen
// 4.26	N/A	normal	N/A	N/A	FALSE	normal	one char	FALSE	Varying Content with Input String chosen
// 4.27	N/A	normal	N/A	N/A	FALSE	normal	super long	FALSE	Varying Content with Input String chosen
// 4.28	N/A	normal	N/A	N/A	FALSE	normal	multi chars	TRUE	Varying Should Match with Input String chosen
// 4.29	N/A	N/A	multi-element array	N/A	FALSE	normal	multi chars	FALSE	Base Case with Unordered input Char Array chosen
// 4.30	N/A	N/A	Empty array	N/A	FALSE	normal	multi chars	FALSE	Varying Unordered input Char Array with Unordered input Char Array chosen
// 4.31	N/A	N/A	one-element array	N/A	FALSE	normal	multi chars	FALSE	Varying Unordered input Char Array with Unordered input Char Array chosen
// 4.32	N/A	N/A	sorted multi-element array	N/A	FALSE	normal	multi chars	FALSE	Varying Unordered input Char Array with Unordered input Char Array chosen
// 4.33	N/A	N/A	super long array	N/A	FALSE	normal	multi chars	FALSE	Varying Unordered input Char Array with Unordered input Char Array chosen
// 4.34	N/A	N/A	multi-element array	N/A	FALSE	0	multi chars	FALSE	Varying Position with Unordered input Char Array chosen
// 4.35	N/A	N/A	multi-element array	N/A	FALSE	1	multi chars	FALSE	Varying Position with Unordered input Char Array chosen
// 4.36	N/A	N/A	multi-element array	N/A	FALSE	second last	multi chars	FALSE	Varying Position with Unordered input Char Array chosen
// 4.37	N/A	N/A	multi-element array	N/A	FALSE	last	multi chars	FALSE	Varying Position with Unordered input Char Array chosen
// 4.38	N/A	N/A	multi-element array	N/A	FALSE	finished	multi chars	FALSE	Varying Position with Unordered input Char Array chosen
// 4.39	N/A	N/A	multi-element array	N/A	FALSE	normal	Empty	FALSE	Varying Content with Unordered input Char Array chosen
// 4.40	N/A	N/A	multi-element array	N/A	FALSE	normal	one char	FALSE	Varying Content with Unordered input Char Array chosen
// 4.41	N/A	N/A	multi-element array	N/A	FALSE	normal	super long	FALSE	Varying Content with Unordered input Char Array chosen
// 4.42	N/A	N/A	multi-element array	N/A	FALSE	normal	multi chars	TRUE	Varying Should Match with Unordered input Char Array chosen
// 4.43	N/A	N/A	N/A	multi-element array	FALSE	normal	multi chars	FALSE	Base Case with Ordered input Char Array chosen
// 4.44	N/A	N/A	N/A	Empty array	FALSE	normal	multi chars	FALSE	Varying Ordered input Char Array with Ordered input Char Array chosen
// 4.45	N/A	N/A	N/A	one-element array	FALSE	normal	multi chars	FALSE	Varying Ordered input Char Array with Ordered input Char Array chosen
// 4.46	N/A	N/A	N/A	super long array	FALSE	normal	multi chars	FALSE	Varying Ordered input Char Array with Ordered input Char Array chosen
// 4.47	N/A	N/A	N/A	multi-element array	FALSE	0	multi chars	FALSE	Varying Position with Ordered input Char Array chosen
// 4.48	N/A	N/A	N/A	multi-element array	FALSE	1	multi chars	FALSE	Varying Position with Ordered input Char Array chosen
// 4.49	N/A	N/A	N/A	multi-element array	FALSE	second last	multi chars	FALSE	Varying Position with Ordered input Char Array chosen
// 4.50	N/A	N/A	N/A	multi-element array	FALSE	last	multi chars	FALSE	Varying Position with Ordered input Char Array chosen
// 4.51	N/A	N/A	N/A	multi-element array	FALSE	finished	multi chars	FALSE	Varying Position with Ordered input Char Array chosen
// 4.52	N/A	N/A	N/A	multi-element array	FALSE	normal	Empty	FALSE	Varying Content with Ordered input Char Array chosen
// 4.53	N/A	N/A	N/A	multi-element array	FALSE	normal	one char	FALSE	Varying Content with Ordered input Char Array chosen
// 4.54	N/A	N/A	N/A	multi-element array	FALSE	normal	super long	FALSE	Varying Content with Ordered input Char Array chosen
// 4.55	N/A	N/A	N/A	multi-element array	FALSE	normal	multi chars	TRUE	Varying Should Match with Ordered input Char Array chosen

public class CharacterReaderSpecTest4 {

	@Test
	public void testMatchesCharBase() {
		String context = "\r1fH$";
		CharacterReader r = new CharacterReader(context);
		for (int i = 0; i < 2; i++) {
			r.consume();
		}
		boolean expected = false;
		char input = 'm';
		boolean actual = r.matches(input);
		assertEquals(expected, actual);
	}

	@Test
	public void testMatchesCharWithUppercaseChar() {
		String context = "M9T-B";
		CharacterReader r = new CharacterReader(context);
		for (int i = 0; i < 2; i++) {
			r.consume();
		}
		boolean expected = false;
		char input = 'M';
		boolean actual = r.matches(input);
		assertEquals(expected, actual);
	}

	@Test
	public void testMatchesCharWithNumberChar() {
		String context = "r\nm<G";
		CharacterReader r = new CharacterReader(context);
		for (int i = 0; i < 2; i++) {
			r.consume();
		}
		boolean expected = false;
		char input = '4';
		boolean actual = r.matches(input);
		assertEquals(expected, actual);
	}

	@Test
	public void testMatchesCharWithWhitespaceChar() {
		String context = "m~j^Q";
		CharacterReader r = new CharacterReader(context);
		for (int i = 0; i < 2; i++) {
			r.consume();
		}
		boolean expected = false;
		char input = '\n';
		boolean actual = r.matches(input);
		assertEquals(expected, actual);
	}

	@Test
	public void testMatchesCharWithPunctuationChar() {
		String context = "T3O;>";
		CharacterReader r = new CharacterReader(context);
		for (int i = 0; i < 2; i++) {
			r.consume();
		}
		boolean expected = false;
		char input = '\'';
		boolean actual = r.matches(input);
		assertEquals(expected, actual);
	}

	@Test
	public void testMatchesCharWithPosition0() {
		String context = "94%Hh";
		CharacterReader r = new CharacterReader(context);

		boolean expected = false;
		char input = 'r';
		boolean actual = r.matches(input);
		assertEquals(expected, actual);
	}

	@Test
	public void testMatchesCharWithPosition1() {
		String context = ")bl}Z";
		CharacterReader r = new CharacterReader(context);
		for (int i = 0; i < 1; i++) {
			r.consume();
		}
		boolean expected = false;
		char input = 'z';
		boolean actual = r.matches(input);
		assertEquals(expected, actual);
	}

	@Test
	public void testMatchesCharWithPositionSecondLast() {
		String context = "\\=a;\"";
		CharacterReader r = new CharacterReader(context);
		for (int i = 0; i < 3; i++) {
			r.consume();
		}
		boolean expected = false;
		char input = 'w';
		boolean actual = r.matches(input);
		assertEquals(expected, actual);
	}

	@Test
	public void testMatchesCharWithPositionLast() {
		String context = "F~P{i";
		CharacterReader r = new CharacterReader(context);
		for (int i = 0; i < 4; i++) {
			r.consume();
		}
		boolean expected = false;
		char input = 'k';
		boolean actual = r.matches(input);
		assertEquals(expected, actual);
	}

	@Test
	public void testMatchesCharWithPositionFinished() {
		String context = "+CEs(";
		CharacterReader r = new CharacterReader(context);
		for (int i = 0; i < 5; i++) {
			r.consume();
		}
		boolean expected = false;
		char input = 'g';
		boolean actual = r.matches(input);
		assertEquals(expected, actual);
	}

	@Test
	public void testMatchesCharWithContentEmpty() {
		String context = "";
		CharacterReader r = new CharacterReader(context);

		boolean expected = false;
		char input = 'o';
		boolean actual = r.matches(input);
		assertEquals(expected, actual);
	}

	@Test
	public void testMatchesCharWithContentOneChar() {
		String context = "k";
		CharacterReader r = new CharacterReader(context);

		boolean expected = false;
		char input = 'c';
		boolean actual = r.matches(input);
		assertEquals(expected, actual);
	}

	@Test
	public void testMatchesCharWithContentSuperLong() {
		String context = "$!nM)L{p)Q(A:).K4l/XO,ELxyxe;[H8il]\nqtek`(^Y{&J%EB";
		CharacterReader r = new CharacterReader(context);
		for (int i = 0; i < 45; i++) {
			r.consume();
		}
		boolean expected = false;
		char input = 'k';
		boolean actual = r.matches(input);
		assertEquals(expected, actual);
	}

	@Test
	public void testMatchesCharWithShouldMatchTrue() {
		String context = "1-s5\"";
		CharacterReader r = new CharacterReader(context);
		for (int i = 0; i < 2; i++) {
			r.consume();
		}
		boolean expected = true;
		char input = 's';
		boolean actual = r.matches(input);
		assertEquals(expected, actual);
	}

	@Test
	public void testMatchesStringBase() {
		String context = "c~Io{";
		CharacterReader r = new CharacterReader(context);
		for (int i = 0; i < 2; i++) {
			r.consume();
		}
		boolean expected = false;
		String input = "kP;o!.=QyF";
		boolean actual = r.matches(input);
		assertEquals(expected, actual);
	}

	@Test
	public void testMatchesStringWithEmptyString() {
		String context = "VvQ2h";
		CharacterReader r = new CharacterReader(context);
		for (int i = 0; i < 2; i++) {
			r.consume();
		}
		boolean expected = true;
		String input = "";
		boolean actual = r.matches(input);
		assertEquals(expected, actual);
	}

	@Test
	public void testMatchesStringWithShortString() {
		String context = ">\':^j";
		CharacterReader r = new CharacterReader(context);
		for (int i = 0; i < 2; i++) {
			r.consume();
		}
		boolean expected = false;
		String input = "s`Cf,";
		boolean actual = r.matches(input);
		assertEquals(expected, actual);
	}

	@Test
	public void testMatchesStringWithSuperLongString() {
		String context = ".><q|";
		CharacterReader r = new CharacterReader(context);
		for (int i = 0; i < 2; i++) {
			r.consume();
		}
		boolean expected = false;
		String input = "p>y:,pYlVoe:cyx|p9A~h]c(2<mHiCj?MS3xh#7f/m`YzHT~8+v`]A\th]uuR&Gp/4\\wb8^0+$N@TX[Gt*_b6 kQ f(JrE\r9T;K]T";
		boolean actual = r.matches(input);
		assertEquals(expected, actual);
	}

	@Test
	public void testMatchesStringWithIgnoreCaseTrue() {
		String context = "uEC>5";
		CharacterReader r = new CharacterReader(context);
		for (int i = 0; i < 2; i++) {
			r.consume();
		}
		boolean expected = false;
		String input = "x\t1@KA/y`q";
		boolean actual = r.matchesIgnoreCase(input);
		assertEquals(expected, actual);
	}

	@Test
	public void testMatchesStringWithPosition0() {
		String context = "[{fv5";
		CharacterReader r = new CharacterReader(context);

		boolean expected = false;
		String input = "{]+1eZ`+1\t";
		boolean actual = r.matches(input);
		assertEquals(expected, actual);
	}

	@Test
	public void testMatchesStringWithPosition1() {
		String context = "ae\"PN";
		CharacterReader r = new CharacterReader(context);
		for (int i = 0; i < 1; i++) {
			r.consume();
		}
		boolean expected = false;
		String input = "iH`u5&!*:\n";
		boolean actual = r.matches(input);
		assertEquals(expected, actual);
	}

	@Test
	public void testMatchesStringWithPositionSecondLast() {
		String context = "Z]1Oa";
		CharacterReader r = new CharacterReader(context);
		for (int i = 0; i < 3; i++) {
			r.consume();
		}
		boolean expected = false;
		String input = "7g1y)>k}qb";
		boolean actual = r.matches(input);
		assertEquals(expected, actual);
	}

	@Test
	public void testMatchesStringWithPositionLast() {
		String context = "p:@zM";
		CharacterReader r = new CharacterReader(context);
		for (int i = 0; i < 4; i++) {
			r.consume();
		}
		boolean expected = false;
		String input = "Bb|\na]&;my";
		boolean actual = r.matches(input);
		assertEquals(expected, actual);
	}

	@Test
	public void testMatchesStringWithPositionFinished() {
		String context = "5oGr@";
		CharacterReader r = new CharacterReader(context);
		for (int i = 0; i < 5; i++) {
			r.consume();
		}
		boolean expected = false;
		String input = "J_xm8Y=kcJ";
		boolean actual = r.matches(input);
		assertEquals(expected, actual);
	}

	@Test
	public void testMatchesStringWithContentEmpty() {
		String context = "";
		CharacterReader r = new CharacterReader(context);

		boolean expected = false;
		String input = "%@?SotJcff";
		boolean actual = r.matches(input);
		assertEquals(expected, actual);
	}

	@Test
	public void testMatchesStringWithContentOneChar() {
		String context = ":";
		CharacterReader r = new CharacterReader(context);

		boolean expected = false;
		String input = "A^H*OU+f\t`";
		boolean actual = r.matches(input);
		assertEquals(expected, actual);
	}

	@Test
	public void testMatchesStringWithContentSuperLong() {
		String context = "AW.LbrtIQRV|lR\f<efIut-LUY)qLo9~EgNw%~jMZQM1nm*98RR";
		CharacterReader r = new CharacterReader(context);
		for (int i = 0; i < 9; i++) {
			r.consume();
		}
		boolean expected = false;
		String input = "@\"|?6>3V\'w";
		boolean actual = r.matches(input);
		assertEquals(expected, actual);
	}

	@Test
	public void testMatchesStringWithShouldMatchTrue() {
		String context = "tv9o`\"2eMQ ^";
		CharacterReader r = new CharacterReader(context);
		for (int i = 0; i < 2; i++) {
			r.consume();
		}
		boolean expected = true;
		String input = "9o`\"2eMQ ^";
		boolean actual = r.matches(input);
		assertEquals(expected, actual);
	}

	@Test
	public void testMatchesCharArrayBase() {
		String context = "hXbmY";
		CharacterReader r = new CharacterReader(context);
		for (int i = 0; i < 2; i++) {
			r.consume();
		}
		boolean expected = false;
		char[] input = { 'i', 'k', 'z', 't', 'C' };
		boolean actual = r.matchesAny(input);
		assertEquals(expected, actual);
	}

	@Test
	public void testMatchesCharArrayWithEmptyArray() {
		String context = "5!-|]";
		CharacterReader r = new CharacterReader(context);
		for (int i = 0; i < 2; i++) {
			r.consume();
		}
		boolean expected = true;
		char[] input = {};
		boolean actual = r.matchesAny(input);
		assertEquals(expected, actual);
	}

	@Test
	public void testMatchesCharArrayWithOneElementArray() {
		String context = "kVCH-";
		CharacterReader r = new CharacterReader(context);
		for (int i = 0; i < 2; i++) {
			r.consume();
		}
		boolean expected = false;
		char[] input = { '2' };
		boolean actual = r.matchesAny(input);
		assertEquals(expected, actual);
	}

	@Test
	public void testMatchesCharArrayWithSortedMultiElementArray() {
		String context = "`d&5\n";
		CharacterReader r = new CharacterReader(context);
		for (int i = 0; i < 2; i++) {
			r.consume();
		}
		boolean expected = false;
		char[] input = { '3', 'T', 'i', 'o', 'y' };
		boolean actual = r.matchesAny(input);
		assertEquals(expected, actual);
	}

	@Test
	public void testMatchesCharArrayWithSuperLongArray() {
		String context = "[a xM";
		CharacterReader r = new CharacterReader(context);
		for (int i = 0; i < 2; i++) {
			r.consume();
		}
		boolean expected = false;
		char[] input = { 't', 'U', '6', 'l', 'B', 'b', 'R', '5', ':', 'Q', 'n', 'e', '"', '8', 'G', 'V', '?', 'i', '2',
				'c', '%', 'X', '|', 'u', '\'', ';', 'M', 'f', '9', '1', 'P', '+', 'r', '#', 'z', '/', 'p', 'j', '{',
				'A', 's', '.', 'Z', '@', 'Y', 'D', '"', 'n', '!', '&', 'O', 'L', 'q', 'k', '>', 'C', '-', '^', 'W', '<',
				'(', 't' };
		boolean actual = r.matchesAny(input);
		assertEquals(expected, actual);
	}

	@Test
	public void testMatchesCharArrayWithPosition0() {
		String context = "\t0XXh";
		CharacterReader r = new CharacterReader(context);

		boolean expected = false;
		char[] input = { 'Q', 'i', '"', 'H', 'M' };
		boolean actual = r.matchesAny(input);
		assertEquals(expected, actual);
	}

	@Test
	public void testMatchesCharArrayWithPosition1() {
		String context = "~Qhe9";
		CharacterReader r = new CharacterReader(context);
		for (int i = 0; i < 1; i++) {
			r.consume();
		}
		boolean expected = false;
		char[] input = { 'u', '?', 'q', 'E', 'K' };
		boolean actual = r.matchesAny(input);
		assertEquals(expected, actual);
	}

	@Test
	public void testMatchesCharArrayWithPositionSecondLast() {
		String context = "k]`tT";
		CharacterReader r = new CharacterReader(context);
		for (int i = 0; i < 3; i++) {
			r.consume();
		}
		boolean expected = false;
		char[] input = { '1', 's', '!', ':', '|' };
		boolean actual = r.matchesAny(input);
		assertEquals(expected, actual);
	}

	@Test
	public void testMatchesCharArrayWithPositionLast() {
		String context = "6gm8\f";
		CharacterReader r = new CharacterReader(context);
		for (int i = 0; i < 4; i++) {
			r.consume();
		}
		boolean expected = false;
		char[] input = { '0', 'e', ';', '7', 'X' };
		boolean actual = r.matchesAny(input);
		assertEquals(expected, actual);
	}

	@Test
	public void testMatchesCharArrayWithPositionFinished() {
		String context = "q=Pn`";
		CharacterReader r = new CharacterReader(context);
		for (int i = 0; i < 5; i++) {
			r.consume();
		}
		boolean expected = false;
		char[] input = { 't', 'c', 'e', '/', '<' };
		boolean actual = r.matchesAny(input);
		assertEquals(expected, actual);
	}

	@Test
	public void testMatchesCharArrayWithContentEmpty() {
		String context = "";
		CharacterReader r = new CharacterReader(context);

		boolean expected = false;
		char[] input = { ')', '@', 'S', 'y', 'X' };
		boolean actual = r.matchesAny(input);
		assertEquals(expected, actual);
	}

	@Test
	public void testMatchesCharArrayWithContentOneChar() {
		String context = ";";
		CharacterReader r = new CharacterReader(context);

		boolean expected = false;
		char[] input = { 'f', 'f', '!', 'o', 'h' };
		boolean actual = r.matchesAny(input);
		assertEquals(expected, actual);
	}

	@Test
	public void testMatchesCharArrayWithContentSuperLong() {
		String context = "@p^|<LqXL\t^p%yeY4V\ny6T=jff!Gd%\\+,BDl\f=\f#`&1#Nos2+2";
		CharacterReader r = new CharacterReader(context);
		for (int i = 0; i < 7; i++) {
			r.consume();
		}
		boolean expected = false;
		char[] input = { '`', '7', ';', '=', 'R' };
		boolean actual = r.matchesAny(input);
		assertEquals(expected, actual);
	}

	@Test
	public void testMatchesCharArrayWithShouldMatchTrue() {
		String context = "\ftmd5";
		CharacterReader r = new CharacterReader(context);
		for (int i = 0; i < 2; i++) {
			r.consume();
		}
		boolean expected = true;
		char[] input = { 'n', '1', 'i', 'm', '1' };
		boolean actual = r.matchesAny(input);
		assertEquals(expected, actual);
	}

	@Test
	public void testMatchesCharArraySortedBase() {
		String context = "kTjpT";
		CharacterReader r = new CharacterReader(context);
		for (int i = 0; i < 2; i++) {
			r.consume();
		}
		boolean expected = false;
		char[] input = { '"', 'G', 'P', 'd', '}' };
		java.util.Arrays.sort(input);
		boolean actual = r.matchesAnySorted(input);
		assertEquals(expected, actual);
	}

	@Test
	public void testMatchesCharArraySortedWithEmptyArray() {
		String context = "SSwbD";
		CharacterReader r = new CharacterReader(context);
		for (int i = 0; i < 2; i++) {
			r.consume();
		}
		boolean expected = true;
		char[] input = {};
		java.util.Arrays.sort(input);
		boolean actual = r.matchesAnySorted(input);
		assertEquals(expected, actual);
	}

	@Test
	public void testMatchesCharArraySortedWithOneElementArray() {
		String context = "/sAaA";
		CharacterReader r = new CharacterReader(context);
		for (int i = 0; i < 2; i++) {
			r.consume();
		}
		boolean expected = false;
		char[] input = { 'j' };
		java.util.Arrays.sort(input);
		boolean actual = r.matchesAnySorted(input);
		assertEquals(expected, actual);
	}

	@Test
	public void testMatchesCharArraySortedWithSuperLongArray() {
		String context = "x\t\\3w";
		CharacterReader r = new CharacterReader(context);
		for (int i = 0; i < 2; i++) {
			r.consume();
		}
		boolean expected = false;
		char[] input = { 't', 'n', '$', '&', '\'', ')', '*', '-', '.', '/', '0', '1', '2', '4', '5', '6', '8', '9', ':',
				'=', '>', 'A', 'C', 'D', 'E', 'F', 'H', 'J', 'L', 'M', 'N', 'O', 'P', 'Q', 'S', 'T', 'U', 'V', 'W', 'Y',
				'Z', '_', '`', 'a', 'b', 'c', 'd', 'f', 'h', 'j', 'k', 'l', 'n', 'o', 'q', 's', 't', 'u', 'x', '{',
				'~' };
		java.util.Arrays.sort(input);
		boolean actual = r.matchesAnySorted(input);
		assertEquals(expected, actual);
	}

	@Test
	public void testMatchesCharArraySortedWithPosition0() {
		String context = "=>l9\n";
		CharacterReader r = new CharacterReader(context);

		boolean expected = false;
		char[] input = { '%', '7', 'R', 'T', 's' };
		java.util.Arrays.sort(input);
		boolean actual = r.matchesAnySorted(input);
		assertEquals(expected, actual);
	}

	@Test
	public void testMatchesCharArraySortedWithPosition1() {
		String context = "<7aBM";
		CharacterReader r = new CharacterReader(context);
		for (int i = 0; i < 1; i++) {
			r.consume();
		}
		boolean expected = false;
		char[] input = { '5', 'A', 'L', '`', 'a' };
		java.util.Arrays.sort(input);
		boolean actual = r.matchesAnySorted(input);
		assertEquals(expected, actual);
	}

	@Test
	public void testMatchesCharArraySortedWithPositionSecondLast() {
		String context = ":t2{\n";
		CharacterReader r = new CharacterReader(context);
		for (int i = 0; i < 3; i++) {
			r.consume();
		}
		boolean expected = false;
		char[] input = { 'v', '(', '=', 'M', 'o' };
		java.util.Arrays.sort(input);
		boolean actual = r.matchesAnySorted(input);
		assertEquals(expected, actual);
	}

	@Test
	public void testMatchesCharArraySortedWithPositionLast() {
		String context = "D(\rZJ";
		CharacterReader r = new CharacterReader(context);
		for (int i = 0; i < 4; i++) {
			r.consume();
		}
		boolean expected = false;
		char[] input = { 'r', '"', '8', 'l' };
		java.util.Arrays.sort(input);
		boolean actual = r.matchesAnySorted(input);
		assertEquals(expected, actual);
	}

	@Test
	public void testMatchesCharArraySortedWithPositionFinished() {
		String context = "faGY&";
		CharacterReader r = new CharacterReader(context);
		for (int i = 0; i < 5; i++) {
			r.consume();
		}
		boolean expected = false;
		char[] input = { '=', 'I', '"', 'c', 'p' };
		java.util.Arrays.sort(input);
		boolean actual = r.matchesAnySorted(input);
		assertEquals(expected, actual);
	}

	@Test
	public void testMatchesCharArraySortedWithContentEmpty() {
		String context = "";
		CharacterReader r = new CharacterReader(context);

		boolean expected = false;
		char[] input = { '-', '4', 'Y', 'n', '{' };
		java.util.Arrays.sort(input);
		boolean actual = r.matchesAnySorted(input);
		assertEquals(expected, actual);
	}

	@Test
	public void testMatchesCharArraySortedWithContentOneChar() {
		String context = "]";
		CharacterReader r = new CharacterReader(context);

		boolean expected = false;
		char[] input = { 't', 'n', 'G', 'K', 'T' };
		java.util.Arrays.sort(input);
		boolean actual = r.matchesAnySorted(input);
		assertEquals(expected, actual);
	}

	@Test
	public void testMatchesCharArraySortedWithContentSuperLong() {
		String context = "HN\'Rp&F\rui1LK%r,%=A\'n0>(Z \fJL4V+=rup`pWZ.7r*\\MT=8\t";
		CharacterReader r = new CharacterReader(context);
		for (int i = 0; i < 28; i++) {
			r.consume();
		}
		boolean expected = false;
		char[] input = { '/', 'e', 'j', 'k', 'z' };
		java.util.Arrays.sort(input);
		boolean actual = r.matchesAnySorted(input);
		assertEquals(expected, actual);
	}

	@Test
	public void testMatchesCharArraySortedWithShouldMatchTrue() {
		String context = "O\"#h4";
		CharacterReader r = new CharacterReader(context);
		for (int i = 0; i < 2; i++) {
			r.consume();
		}
		boolean expected = true;
		char[] input = { '!', '#', '9', 'B', '_' };
		java.util.Arrays.sort(input);
		
		boolean actual = r.matchesAnySorted(input);
		assertEquals(expected, actual);
	}
}