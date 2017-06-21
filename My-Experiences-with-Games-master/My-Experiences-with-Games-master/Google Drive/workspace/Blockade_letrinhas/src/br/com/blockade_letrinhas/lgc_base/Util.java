package br.com.blockade_letrinhas.lgc_base;

public class Util {

	public static boolean colide(ElementoGame a, ElementoGame b) {
		if (!a.isAtivo() || !b.isAtivo())
			return false;

		final int plA = a.getPos_x() + a.getLargura();
		final int plB = b.getPos_x() + b.getLargura();
		final int paA = a.getPos_y() + a.getAltura();
		final int paB = b.getPos_y() + b.getAltura();

		if (plA > b.getPos_x() && a.getPos_x() < plB && paA > b.getPos_y() && a.getPos_y() < paB) {
			return true;
		}

		return false;
	}

	public static boolean colideX(ElementoGame a, ElementoGame b) {
		if (!a.isAtivo() || !b.isAtivo())
			return false;

		if (a.getPos_x() + a.getLargura() >= b.getPos_x() && a.getPos_x() <= b.getPos_x() + b.getLargura()) {
			return true;
		}

		return false;
	}

	public static void centraliza(ElementoGame el, int larg, int alt) {
		if (alt > 0)
			el.setPos_y(alt / 2 - el.getAltura() / 2);

		if (larg > 0)
			el.setPos_x(larg / 2 - el.getLargura() / 2);

	}

	public static boolean saiu(ElementoGame e, int largura, int altura) {
		if (e.getPos_x() < 0 || e.getPos_x() + e.getLargura() > largura)
			return true;

		if (e.getPos_y() < 0 || e.getPos_y() + e.getAltura() > altura)
			return true;

		return false;
	}

}
