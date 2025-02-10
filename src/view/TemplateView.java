package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
public abstract class TemplateView extends JFrame {
    protected JPanel setorPrincipal;
    protected JPanel setorHeader;
    protected JPanel setorConteudo;
    protected JPanel setorFooter;
    protected JLabel setorTitulo;
    protected JButton botaoVoltar;

    public TemplateView(String titulo){
        super(titulo);
        initializeComponents();
        setupLayout();
        setupEvents();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(800,600));
        setLocationRelativeTo(null);
    }

    private void initializeComponents(){
        setorPrincipal = new JPanel(new BorderLayout(10,10));
        setorHeader = new JPanel(new BorderLayout());
        setorConteudo = new JPanel(new BorderLayout(10,10));
        setorFooter = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        setorTitulo = new JLabel(getTitle());
        setorTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        botaoVoltar = new JButton("⇽");

        setorPrincipal.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        setorHeader.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        setorConteudo.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        setorFooter.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

    }

    private void setupLayout(){
        setorHeader.add(setorTitulo, BorderLayout.CENTER);
        setorHeader.add(botaoVoltar, BorderLayout.WEST);
        setorHeader.setBackground(new Color(240, 240, 240, 240));

        setorPrincipal.add(setorHeader, BorderLayout.NORTH);
        setorPrincipal.add(setorConteudo, BorderLayout.CENTER);
        setorPrincipal.add(setorFooter, BorderLayout.SOUTH);

        add(setorPrincipal);
    }

    protected void setupEvents(){
        botaoVoltar.addActionListener(e -> aoVoltar() );

        addWindowListener(new WindowAdapter() {

            public void fecharJanela(WindowEvent e) {
                fechandoJanela();
            }
        });
    }

    protected void aoVoltar(){
        dispose();
    }
    protected void fechandoJanela(){
        /////
    }

    protected void adicionarConteudo(Component componente){
        setorConteudo.add(componente, BorderLayout.CENTER);

    }

    protected void adicionarAoRodape(Component componente){
        setorFooter.add(componente);

    }

    protected void setLayoutDiferente(LayoutManager layout){
        setorConteudo.setLayout(layout);
    }

}
