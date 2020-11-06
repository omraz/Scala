/*
 *	Version Date	Note
 *	0.0.1	06Nov20	Leasing se plati 25. mesic predem
 *
 */

package scala;

import java.util.Calendar;
import java.text.SimpleDateFormat;

public class Leasing	{
	
	Leasing()	{
		System.out.println("Scala Leasing   version 0.0.1");

		final double first = 1912.91;
		final double regular = 6014.97;
		final double last = 4256.94;
		double pct;
		double paid;
		double pay;

		Calendar begin = Calendar.getInstance();
		begin.set(2020, 5, 1);

		Calendar today = Calendar.getInstance();

		Calendar retire = Calendar.getInstance();
		retire.set(2024, 6, 1);

		Calendar current = Calendar.getInstance();
		current.set(begin.get(Calendar.YEAR), 0, 1);

		SimpleDateFormat fmtMonth = new SimpleDateFormat("MMM");

		int month;
		int paidCnt = 0;
		int payCnt = 0;

		do	{
			month = current.get(Calendar.MONTH);
			if (month == Calendar.JANUARY)	{
				System.out.print("\n" + current.get(Calendar.YEAR) + "  ");				
			}
			if (current.getTime().compareTo(begin.getTime()) < 0)	{
				System.out.printf("    ");
			}
			else if (current.getTime().compareTo(today.getTime()) < 0)	{
				System.out.printf("  X ");
				//System.out.printf("  %c ", 0x00b0);
				paidCnt++;
			}
			else {
				//System.out.printf("  %02d", current.get(Calendar.MONTH) + 1);
				System.out.printf(" " + fmtMonth.format(current.getTime()));
				payCnt++;
			}
			
			current.add(Calendar.MONTH, 1);
		} while (current.getTime().compareTo(retire.getTime()) < 0);
		
		System.out.println();
		
		paid = first + (paidCnt - 1) * regular;
		pay = first + last + (paidCnt + payCnt - 2) * regular;
		System.out.printf("%9.2f / %9.2f\n", paid, pay);
		
		pct = paid * 100 / pay;
		System.out.printf("[");
		for (long i = 0; i < 80; i++)	{
			//System.out.printf(i < (pct * 8 / 10) ? "\u2588" : ".");
			System.out.printf(i < (pct * 8 / 10) ? "#" : ".");
		}
		System.out.printf("%2.0f%%]\n", pct);
	}
}



