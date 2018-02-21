package controlador;

import java.awt.EventQueue;

import vista.AhorcadoUI;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Principal extends AhorcadoUI {

	Control control = new Control();
	private final int NUMERO_FALLOS = 6;

	public Principal() {
		super();
		txtLetra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtMensaje.setText("");
				// Obtener la letra pedida
				if (txtLetra.getText().isEmpty()) {
					txtMensaje.setText("Introduce una letra");
				} else {
					control.anotarAciertos(txtLetra.getText().charAt(0));
					txtAciertos.setText(control.getAciertos().toString());
				}
				// Comprobar que el n�mero de fallos est� dentro del l�mite
				if (control.getFallos() < NUMERO_FALLOS && !control.palabraAcertada()) {
					if (control.palabraAcertada()) {
						txtMensaje.setText("Palabra acertada!");
					}
					// Borrar la letra tras ponerla
					txtLetra.setText("");
				} else if (control.getFallos() == NUMERO_FALLOS) {
					txtLetra.setEnabled(false);
					txtAciertos.setText(control.getPalabreja());
					txtMensaje.setText("Has perdido.");
				}
				// Comprobar si se ha ganado el juego
				if (control.palabraAcertada()) {
					txtLetra.setEnabled(false);
					txtMensaje.setText("Has ganado!");
				}
				// N�mero de fallos
			txtFallos.setText(String.valueOf(control.getFallos()));
			}
		});

		// Actuadores del boton Ya.
		btnYa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtPalabraSecreta.getText().isEmpty())
					txtMensaje.setText("Escribe algo");
				else {
					// aqui hemos llegado porque la palabra es valida
					control.setPalabreja(txtPalabraSecreta.getText());
					control.iniciaAciertos();
					// comportamiento del ui tras validar palabra
					// Ocultar los componentes que se han usado para introducir la
					// palabra secreta
					txtPalabraSecreta.setVisible(false);
					lblPalabraSecreta.setVisible(false);
					btnYa.setVisible(false);
					// hacemos visible el titulo
					lblTitulo.setVisible(true);
					// habilitar el txt de la letra
					txtLetra.setEditable(true);
					txtMensaje.setText("");
					// Poner la palabra oculta y los fallos
					txtAciertos.setText(control.getAciertos());
					txtFallos.setText(String.valueOf(control.getFallos()));
				}
			}
		});

	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// IMPORTANTE: Se crea un objeto de Principal no del UI
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
