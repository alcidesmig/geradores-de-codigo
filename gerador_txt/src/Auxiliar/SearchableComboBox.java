package Auxiliar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;

public class SearchableComboBox extends PlainDocument {

    private static final long serialVersionUID = 4956303094169423395L;

    JComboBox comboBox;
    ComboBoxModel model;
    JTextComponent editor;
    boolean selecting = false;
    StringBuilder caracteresParaBusca = new StringBuilder();
    int teclaAtual = 0;

    public SearchableComboBox(final JComboBox comboBox) {
        this.comboBox = comboBox;
        model = comboBox.getModel();
        editor = (JTextComponent) comboBox.getEditor().getEditorComponent();

        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!selecting) {
                    highlightCompletedText(0);
                }
            }
        });
        editor.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                teclaAtual = e.getKeyCode();
                String digitado = e.getKeyChar() + "";
                if (digitado.matches("[A-Za-z0-9 -.,/]")) {
                    caracteresParaBusca.append(e.getKeyChar());
                }

                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        insertString(0, caracteresParaBusca.toString(), null);
                    } catch (BadLocationException e1) {
                        e1.printStackTrace();
                    }
                }

                if (comboBox.isDisplayable()) {
                    comboBox.setPopupVisible(true);
                }
            }
        });
    }

    public void remove(int offs, int len) throws BadLocationException {
        if (selecting) {
            return;
        }
        super.remove(offs, len);
    }

    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        if (selecting) {
            return;
        }
        super.insertString(offs, str, a);
        Object item = null;

        if (teclaAtual == KeyEvent.VK_ENTER) {
            item = lookupItem(caracteresParaBusca.toString());
            caracteresParaBusca.delete(0, caracteresParaBusca.length());
        } else {
            return;
        }

        if (item != null) {
            setSelectedItem(item);
        } else {
            item = comboBox.getSelectedItem();
            offs = offs - str.length();
            comboBox.getToolkit().beep();
        }
        setText(item.toString());
    }

    private void setText(String text) throws BadLocationException {
        super.remove(0, getLength());
        super.insertString(0, text, null);
    }

    private void highlightCompletedText(int start) {
        editor.setSelectionStart(start);
        editor.setSelectionEnd(getLength());
    }

    private void setSelectedItem(Object item) {
        selecting = true;
        model.setSelectedItem(item);
        selecting = false;
    }

    private Object lookupItem(String pattern) {
        String buscar = pattern;
        Object selectedItem = model.getSelectedItem();
        if (selectedItem != null) {
            buscar = caracteresParaBusca.toString();
        }

        for (int i = 0, n = model.getSize(); i < n; i++) {
            Object currentItem = model.getElementAt(i);
            if (qualquerParte(currentItem.toString(), buscar)) {
                return currentItem;
            }
        }

        return null;
    }

    private boolean qualquerParte(String str1, String str2) {
        return str1.toUpperCase().contains(str2.toUpperCase());
    }
}
