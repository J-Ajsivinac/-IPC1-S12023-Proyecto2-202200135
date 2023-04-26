package Interfaz;

import Modificadores.MisClases.DatosCategoria;
import Modificadores.MisClases.ListaSimple;
import Modificadores.MisClases.Usuario;
import Modificadores.MisClases.ctrlUsuario;
import Modificadores.MisClases.ejecutarP;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Color;
import java.io.File;
import java.util.function.Function;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;

/**
 *
 * @author mesoi
 */
public class Convertidor extends javax.swing.JFrame {

    private FlatSVGIcon.ColorFilter fl;
    private DefaultListModel<String> model = new DefaultListModel<>();
    private DefaultListModel<String> modelA = new DefaultListModel<>();
    private ListaSimple<String> listaP = new ListaSimple<>();
    private ListaSimple<Integer> op = new ListaSimple<>();

    /**
     * Creates new form Convertidor
     */
    public Convertidor() {
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("Convertidor");
        consola.putClientProperty("JComponent.roundRect", true);
        progreso.setStringPainted(true);
        FlatSVGIcon add = new FlatSVGIcon("img/add.svg", 12, 12);
        fl = new FlatSVGIcon.ColorFilter(new Function<Color, Color>() {
            @Override
            public Color apply(Color t) {
                return new Color(25, 33, 40);
            }

        });
        add.setColorFilter(fl);
        btnagregar.setIcon(add);
        cargarUsuarios();
        procesamiento.setFocusable(false);
        procesamiento.disable();
        //cargarCategoriasU();
    }

    public void cargarUsuarios() {
        comboUsuarios.removeAllItems();
        for (int i = 0; i < ctrlUsuario.usuarios.getSize(); i++) {
            Usuario temp = (Usuario) ctrlUsuario.usuarios.get(i);
            comboUsuarios.addItem(temp.getNombre());
        }
    }

    public void cargarCategoriasU() {
        comboCategorias.removeAllItems();
        int seleccion = comboUsuarios.getSelectedIndex();
        Usuario temp = (Usuario) ctrlUsuario.usuarios.get(seleccion);
        if (temp != null) {
            for (int i = 0; i < temp.getCategoria().size(); i++) {
                DatosCategoria dTemp = temp.getCategoria().get(i);
                comboCategorias.addItem(dTemp.getNombreCategoria());
            }
        }
    }

    public void agregarCola() {
        int usuarioS = comboUsuarios.getSelectedIndex();
        int categoriaS = comboCategorias.getSelectedIndex();

        Usuario temp = (Usuario) ctrlUsuario.usuarios.get(usuarioS);
        DatosCategoria dTemp = temp.getCategoria().get(categoriaS);
        for (int i = 0; i < dTemp.getImgCategoria().getSize(); i++) {

            File fTemp = new File(dTemp.getImgCategoria().get(i) + "");
            model.addElement(fTemp.getName() + "");
            modelA.addElement(fTemp.getPath() + "");
        }
        procesamiento.setModel(model);
    }

    public void ejecutarFiltros() {
        progreso.setValue(0);
        JCheckBox[] opciones = {check1, check2, check3, check4, check5};

        for (int i = 0; i < opciones.length; i++) {
            if (opciones[i].isSelected()) {
                switch (i) {
                    case 0:
                        for (int j = 0; j < modelA.size(); j++) {
                            listaP.add(modelA.get(j));
                            op.add(i + 1);
                        }
                        break;
                    case 1:
                        for (int j = 0; j < modelA.size(); j++) {
                            listaP.add(modelA.get(j));
                            op.add(i + 1);
                        }
                        break;
                    case 2:
                        for (int j = 0; j < modelA.size(); j++) {
                            listaP.add(modelA.get(j));
                            op.add(i + 1);
                        }
                        break;
                    case 3:
                        for (int j = 0; j < modelA.size(); j++) {
                            listaP.add(modelA.get(j));
                            op.add(i + 1);
                        }
                        break;
                    case 4:
                        for (int j = 0; j < modelA.size(); j++) {
                            listaP.add(modelA.get(j));
                            op.add(i + 1);
                        }
                        break;
                    default:
                        throw new AssertionError();
                }
            }
        }

        for (int i = 0; i < listaP.getSize(); i++) {
            new ejecutarP((int) op.get(i), (String) listaP.get(i), listaP.getSize()).start();
        }
        model.removeAllElements();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panelRound1 = new Elementos.PanelRound();
        jLabel1 = new javax.swing.JLabel();
        comboUsuarios = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        comboCategorias = new javax.swing.JComboBox<>();
        btnagregar = new Elementos.ButtonRound();
        panelRound2 = new Elementos.PanelRound();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        procesamiento = new javax.swing.JList<>();
        buttonRound1 = new Elementos.ButtonRound();
        panelRound3 = new Elementos.PanelRound();
        panelRound4 = new Elementos.PanelRound();
        jLabel4 = new javax.swing.JLabel();
        check1 = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        panelRound5 = new Elementos.PanelRound();
        jLabel6 = new javax.swing.JLabel();
        check3 = new javax.swing.JCheckBox();
        panelRound6 = new Elementos.PanelRound();
        jLabel7 = new javax.swing.JLabel();
        check2 = new javax.swing.JCheckBox();
        panelRound7 = new Elementos.PanelRound();
        jLabel8 = new javax.swing.JLabel();
        check5 = new javax.swing.JCheckBox();
        panelRound8 = new Elementos.PanelRound();
        jLabel9 = new javax.swing.JLabel();
        check4 = new javax.swing.JCheckBox();
        jLabel10 = new javax.swing.JLabel();
        buttonRound2 = new Elementos.ButtonRound();
        progreso = new javax.swing.JProgressBar();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        consola = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(31, 33, 37));

        panelRound1.setBackground(new java.awt.Color(39, 44, 51));
        panelRound1.setForeground(new java.awt.Color(39, 44, 51));
        panelRound1.setRoundBottomLeft(20);
        panelRound1.setRoundBottomRight(20);
        panelRound1.setRoundTopLeft(20);
        panelRound1.setRoundTopRight(20);

        jLabel1.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Usuario:");

        comboUsuarios.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        comboUsuarios.setForeground(new java.awt.Color(255, 255, 255));
        comboUsuarios.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboUsuarios.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboUsuariosItemStateChanged(evt);
            }
        });
        comboUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboUsuariosActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Categoría:");

        comboCategorias.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        comboCategorias.setForeground(new java.awt.Color(255, 255, 255));
        comboCategorias.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnagregar.setForeground(new java.awt.Color(25, 33, 40));
        btnagregar.setText(" Agregar");
        btnagregar.setBorderColor(new java.awt.Color(53, 223, 145));
        btnagregar.setColor(new java.awt.Color(53, 223, 145));
        btnagregar.setColorClick(new java.awt.Color(62, 182, 127));
        btnagregar.setColorOver(new java.awt.Color(61, 203, 138));
        btnagregar.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        btnagregar.setRadius(20);
        btnagregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnagregarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(comboUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(comboCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(btnagregar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnagregar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(comboUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(comboCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        panelRound2.setBackground(new java.awt.Color(39, 44, 51));
        panelRound2.setForeground(new java.awt.Color(39, 44, 51));
        panelRound2.setRoundBottomLeft(20);
        panelRound2.setRoundBottomRight(20);
        panelRound2.setRoundTopLeft(20);
        panelRound2.setRoundTopRight(20);

        jLabel3.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Cola de Procesamiento");

        procesamiento.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        procesamiento.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(procesamiento);

        buttonRound1.setForeground(new java.awt.Color(184, 192, 230));
        buttonRound1.setText("Regresar");
        buttonRound1.setBorderColor(new java.awt.Color(20, 26, 31));
        buttonRound1.setColor(new java.awt.Color(20, 26, 31));
        buttonRound1.setColorClick(new java.awt.Color(37, 51, 63));
        buttonRound1.setColorOver(new java.awt.Color(26, 35, 43));
        buttonRound1.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        buttonRound1.setRadius(12);
        buttonRound1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRound1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(buttonRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelRound2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 42, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(12, 12, 12))
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonRound1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        panelRound3.setBackground(new java.awt.Color(39, 44, 51));
        panelRound3.setForeground(new java.awt.Color(39, 44, 51));
        panelRound3.setRoundBottomLeft(20);
        panelRound3.setRoundBottomRight(20);
        panelRound3.setRoundTopLeft(20);
        panelRound3.setRoundTopRight(20);

        panelRound4.setBackground(new java.awt.Color(31, 33, 37));
        panelRound4.setForeground(new java.awt.Color(33, 37, 43));
        panelRound4.setRoundBottomLeft(20);
        panelRound4.setRoundBottomRight(20);
        panelRound4.setRoundTopLeft(20);
        panelRound4.setRoundTopRight(20);

        jLabel4.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("JPEG a BMP y Viceversa");

        check1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound4Layout = new javax.swing.GroupLayout(panelRound4);
        panelRound4.setLayout(panelRound4Layout);
        panelRound4Layout.setHorizontalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(check1)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRound4Layout.setVerticalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound4Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(check1, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addGap(9, 9, 9))
        );

        jLabel5.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Selerccione una Opción");

        panelRound5.setBackground(new java.awt.Color(31, 33, 37));
        panelRound5.setForeground(new java.awt.Color(33, 37, 43));
        panelRound5.setRoundBottomLeft(20);
        panelRound5.setRoundBottomRight(20);
        panelRound5.setRoundTopLeft(20);
        panelRound5.setRoundTopRight(20);

        jLabel6.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Copia JPEG");

        javax.swing.GroupLayout panelRound5Layout = new javax.swing.GroupLayout(panelRound5);
        panelRound5.setLayout(panelRound5Layout);
        panelRound5Layout.setHorizontalGroup(
            panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound5Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(check3)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addContainerGap(117, Short.MAX_VALUE))
        );
        panelRound5Layout.setVerticalGroup(
            panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound5Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(check3, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addGap(9, 9, 9))
        );

        panelRound6.setBackground(new java.awt.Color(31, 33, 37));
        panelRound6.setForeground(new java.awt.Color(33, 37, 43));
        panelRound6.setRoundBottomLeft(20);
        panelRound6.setRoundBottomRight(20);
        panelRound6.setRoundTopLeft(20);
        panelRound6.setRoundTopRight(20);

        jLabel7.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Rojo Verde Azul Sepia");

        javax.swing.GroupLayout panelRound6Layout = new javax.swing.GroupLayout(panelRound6);
        panelRound6.setLayout(panelRound6Layout);
        panelRound6Layout.setHorizontalGroup(
            panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound6Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(check2)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addContainerGap(51, Short.MAX_VALUE))
        );
        panelRound6Layout.setVerticalGroup(
            panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(check2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        panelRound7.setBackground(new java.awt.Color(31, 33, 37));
        panelRound7.setForeground(new java.awt.Color(33, 37, 43));
        panelRound7.setRoundBottomLeft(20);
        panelRound7.setRoundBottomRight(20);
        panelRound7.setRoundTopLeft(20);
        panelRound7.setRoundTopRight(20);

        jLabel8.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Blanco y Negro");

        javax.swing.GroupLayout panelRound7Layout = new javax.swing.GroupLayout(panelRound7);
        panelRound7.setLayout(panelRound7Layout);
        panelRound7Layout.setHorizontalGroup(
            panelRound7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound7Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(check5)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addContainerGap(88, Short.MAX_VALUE))
        );
        panelRound7Layout.setVerticalGroup(
            panelRound7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound7Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(panelRound7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(check5, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(9, 9, 9))
        );

        panelRound8.setBackground(new java.awt.Color(31, 33, 37));
        panelRound8.setForeground(new java.awt.Color(33, 37, 43));
        panelRound8.setRoundBottomLeft(20);
        panelRound8.setRoundBottomRight(20);
        panelRound8.setRoundTopLeft(20);
        panelRound8.setRoundTopRight(20);

        jLabel9.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Modificar Imagen");

        javax.swing.GroupLayout panelRound8Layout = new javax.swing.GroupLayout(panelRound8);
        panelRound8.setLayout(panelRound8Layout);
        panelRound8Layout.setHorizontalGroup(
            panelRound8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound8Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(check4)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRound8Layout.setVerticalGroup(
            panelRound8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(check4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel10.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Seleccione el tipo de Ejecución");

        buttonRound2.setForeground(new java.awt.Color(255, 255, 255));
        buttonRound2.setText("Ejecutar en Paralelo");
        buttonRound2.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        buttonRound2.setRadius(20);
        buttonRound2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRound2ActionPerformed(evt);
            }
        });

        progreso.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        progreso.setBorder(null);

        jLabel12.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Consola de Ejecución");

        consola.setEditable(false);
        consola.setColumns(20);
        consola.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        consola.setForeground(new java.awt.Color(255, 255, 255));
        consola.setRows(5);
        jScrollPane3.setViewportView(consola);

        javax.swing.GroupLayout panelRound3Layout = new javax.swing.GroupLayout(panelRound3);
        panelRound3.setLayout(panelRound3Layout);
        panelRound3Layout.setHorizontalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5)
                    .addComponent(jLabel12)
                    .addComponent(jLabel10)
                    .addComponent(buttonRound2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(progreso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRound4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelRound3Layout.createSequentialGroup()
                        .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(panelRound8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelRound6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelRound3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(panelRound5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound3Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(panelRound7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane3))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        panelRound3Layout.setVerticalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelRound4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelRound5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRound6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelRound7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRound8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonRound2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(progreso, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(panelRound2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelRound3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelRound2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRound3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboUsuariosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboUsuariosActionPerformed

    private void check1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_check1ActionPerformed

    private void comboUsuariosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboUsuariosItemStateChanged
        // TODO add your handling code here:
        cargarCategoriasU();
    }//GEN-LAST:event_comboUsuariosItemStateChanged

    private void btnagregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnagregarActionPerformed
        // TODO add your handling code here:
        agregarCola();
    }//GEN-LAST:event_btnagregarActionPerformed

    private void buttonRound2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRound2ActionPerformed
        // TODO add your handling code here:
        ejecutarFiltros();
    }//GEN-LAST:event_buttonRound2ActionPerformed

    private void buttonRound1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRound1ActionPerformed
        // TODO add your handling code here:
        Principal p = new Principal();
        p.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_buttonRound1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Convertidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Convertidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Convertidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Convertidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Convertidor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Elementos.ButtonRound btnagregar;
    private Elementos.ButtonRound buttonRound1;
    private Elementos.ButtonRound buttonRound2;
    private javax.swing.JCheckBox check1;
    private javax.swing.JCheckBox check2;
    private javax.swing.JCheckBox check3;
    private javax.swing.JCheckBox check4;
    private javax.swing.JCheckBox check5;
    private javax.swing.JComboBox<String> comboCategorias;
    private javax.swing.JComboBox<String> comboUsuarios;
    public static javax.swing.JTextArea consola;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private Elementos.PanelRound panelRound1;
    private Elementos.PanelRound panelRound2;
    private Elementos.PanelRound panelRound3;
    private Elementos.PanelRound panelRound4;
    private Elementos.PanelRound panelRound5;
    private Elementos.PanelRound panelRound6;
    private Elementos.PanelRound panelRound7;
    private Elementos.PanelRound panelRound8;
    private javax.swing.JList<String> procesamiento;
    public static javax.swing.JProgressBar progreso;
    // End of variables declaration//GEN-END:variables
}
