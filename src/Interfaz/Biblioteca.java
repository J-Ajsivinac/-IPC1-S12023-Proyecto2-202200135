package Interfaz;

import Modificadores.MisClases.DatosCategoria;
import Modificadores.MisClases.ListaCircular;
import Modificadores.MisClases.ctrlUsuario;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Color;
import java.awt.Image;
import java.awt.MediaTracker;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.function.Function;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author mesoi
 */
public class Biblioteca extends javax.swing.JFrame {

    private FlatSVGIcon.ColorFilter fl;
    private String ruta;
    private boolean nuevo = false;

    /**
     * Creates new form Biblioteca
     */
    public Biblioteca() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Biblioteca");
        DefaultListModel modelo = new DefaultListModel();
        categorias.setModel(modelo);
        nombreU.setText(Principal.credencial.getNombre() + "");
        FlatSVGIcon izquierda = new FlatSVGIcon("img/izquierda.svg", 30, 30);
        FlatSVGIcon derecha = new FlatSVGIcon("img/derecha.svg", 30, 30);

        FlatSVGIcon add = new FlatSVGIcon("img/add.svg", 18, 18);
        FlatSVGIcon eliminar = new FlatSVGIcon("img/eliminar.svg", 18, 18);
        fl = new FlatSVGIcon.ColorFilter(new Function<Color, Color>() {
            @Override
            public Color apply(Color t) {
                return Color.white;
            }

        });

        izquierda.setColorFilter(fl);
        derecha.setColorFilter(fl);
        add.setColorFilter(fl);
        eliminar.setColorFilter(fl);

        btnIzquierda.setIcon(izquierda);
        btnDerecha.setIcon(derecha);
        btnEliminarI.setIcon(eliminar);
        btnAgregarI.setIcon(add);
        cargarCategorias();
        int size = categorias.getModel().getSize();
        if (size != 0) {
            categorias.setSelectedIndex(0);
        }
    }

    public void cargarCategorias() {
        //ctrlUsuario.buscarUsuario(Principal.credencial.getNombre()).getCategoria()
        ArrayList<DatosCategoria> categorias1 = ctrlUsuario.buscarUsuario(Principal.credencial.getNombre()).getCategoria();
        DefaultListModel<String> model = new DefaultListModel<>();
        for (int i = 0; i < categorias1.size(); i++) {
            if (categorias1.get(i) != null) {
                model.addElement(categorias1.get(i).getNombreCategoria());
            }
        }
        categorias.setModel(model);
        categorias.setSelectedIndex(0);
    }

    public void cargarImgCombo() {
        int posicionA = categorias.getSelectedIndex();
        if (posicionA == -1) {
            boxImagenes.removeAllItems();
            return;
        }
        ListaCircular c = ctrlUsuario.buscarUsuario(Principal.credencial.getNombre()).getCategoria().get(posicionA).getImgCategoria();
        int in = c.getIndex();
        System.out.println(c.getIndex() + "-");
        boxImagenes.removeAllItems();
        for (int i = 0; i < c.getSize(); i++) {
            File temp = new File((String) c.get(i));
            boxImagenes.addItem(temp.getName());
        }

        if (c.getSize() != 0) {
            boxImagenes.setSelectedIndex(in);
            System.out.println(c.getIndex() + "-");
        }
    }

    public void agregarC() {
        String nuevoNombre = JOptionPane.showInputDialog(null, "Ingrese el nombre de la categoría");
        if (nuevoNombre == null) {
            return;
        }
        if (!nuevoNombre.trim().isEmpty() && nuevoNombre.trim() != null && nuevoNombre != null) {
            ArrayList<DatosCategoria> categorias1 = Principal.credencial.getCategoria();
            ctrlUsuario.agregarCatUsuario(Principal.posicion, nuevoNombre);
            cargarCategorias();
            actualizarArchivo();
            //Usuario user = ctrlUsuario.buscarUsuario(Principal.credencial.getNombre());
        } else {
            JOptionPane.showMessageDialog(null, "Ingrese un nombre válido");
        }
    }

    public void eliminarC() {
        int confirm = JOptionPane.showConfirmDialog(null, "¿Desea elminar la categoría Actual?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            int size = categorias.getModel().getSize();
            int posicionA = categorias.getSelectedIndex();
            if (size != 0) {
                totalI.setText("0/0");
                ctrlUsuario.eliminarCategoria(Principal.posicion, posicionA);
                cargarCategorias();
                actualizar();
                actualizarArchivo();
            }
        }

    }

    public void agregarFoto() {
        int size = categorias.getModel().getSize();
        if (size == 0) {
            JOptionPane.showMessageDialog(null, "Ingrese una categoría");
            return;
        }
        ruta = "";
        JFileChooser archivos = new JFileChooser();
        archivos.setMultiSelectionEnabled(true);
        File carpeta = new File("C:\\Users\\mesoi\\Documents\\Prueba");
        archivos.setCurrentDirectory(carpeta);

        FileNameExtensionFilter filtrado = new FileNameExtensionFilter("JPG & JPEG", "jpg", "jpeg");
        archivos.setFileFilter(filtrado);

        int respuesta = archivos.showOpenDialog(this);
        if (respuesta == archivos.APPROVE_OPTION) {
            File[] selectedFiles = archivos.getSelectedFiles();

            for (File f : selectedFiles) {
                ruta = f.getPath();
                /// Cargar la imagen original

                ImageIcon originalImageIcon = new ImageIcon(ruta);
                if (originalImageIcon.getImageLoadStatus() != MediaTracker.COMPLETE) {
                    ///htmls/factura.html
                    //originalImageIcon = new ImageIcon("src\\img\\usuario.png");
                    JOptionPane.showMessageDialog(null, "Error al cargar la imagen \n se cargara una imagen por defecto", "Error", JOptionPane.ERROR_MESSAGE);
                }
                int posicionA = categorias.getSelectedIndex();
                //ArrayList<DatosCategoria> categorias1 = Principal.credencial.getCategoria();
                ctrlUsuario.agregarImgUsuario(posicionA, Principal.credencial.getNombre(), ruta);

            }
            int posicionA = categorias.getSelectedIndex();
            ListaCircular c = ctrlUsuario.buscarUsuario(Principal.credencial.getNombre()).getCategoria().get(0).getImgCategoria();
            ArrayList<DatosCategoria> categorias1 = Principal.credencial.getCategoria();
            posicionA = categorias.getSelectedIndex();
            c = ctrlUsuario.buscarUsuario(Principal.credencial.getNombre()).getCategoria().get(posicionA).getImgCategoria();
            cargarImgs((String) c.get(0));
            actualizarArchivo();
            totalI.setText("1/" + c.getSize());
            ruta = "";
            cargarImgCombo();
        }
    }

    public void eliminarFoto() {
        int size = categorias.getModel().getSize();
        if (size == 0) {
            JOptionPane.showMessageDialog(null, "Ingrese una categoría");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(null, "¿Desea elminar la imagen Actual?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            int posicionCategoria = categorias.getSelectedIndex();
            int posicionImagen = boxImagenes.getSelectedIndex();
            ctrlUsuario.eliminarImgUsuario(Principal.posicion, posicionCategoria, posicionImagen);
            int posicionA = categorias.getSelectedIndex();
            ListaCircular c = ctrlUsuario.buscarUsuario(Principal.credencial.getNombre()).getCategoria().get(posicionA).getImgCategoria();
            cargarImgs((String) c.get(posicionImagen));
            actualizarArchivo();
            cargarImgCombo();
        }
    }

    public void cargarImgs(String newUrl) {
        lblimagen.setIcon(null);
        lblimagen.revalidate();
        lblimagen.repaint();

        //String imgRuta = (String) categorias1.get(posicionA).getImgCategoria().get(index);
        String imgRuta = newUrl;
        ImageIcon originalImageIcon = new ImageIcon(imgRuta);

        // Obtener el tamaño original de la imagen
        int originalImageWidth = originalImageIcon.getIconWidth();
        int originalImageHeight = originalImageIcon.getIconHeight();

        // Obtener el tamaño del JLabel
        int labelWidth = lblimagen.getWidth();
        int labelHeight = lblimagen.getHeight();

        // Calcular la relación de aspecto de la imagen original y del JLabel
        double originalImageAspectRatio = (double) originalImageWidth / originalImageHeight;
        double labelAspectRatio = (double) labelWidth / labelHeight;

        // Escalar la imagen si es necesario
        ImageIcon scaledImageIcon;
        if (originalImageAspectRatio > labelAspectRatio) {
            // La imagen es más ancha que el JLabel
            int scaledImageWidth = labelWidth;
            int scaledImageHeight = (int) (scaledImageWidth / originalImageAspectRatio);
            Image scaledImage = originalImageIcon.getImage().getScaledInstance(scaledImageWidth, scaledImageHeight, Image.SCALE_SMOOTH);
            scaledImageIcon = new ImageIcon(scaledImage);
        } else {
            // La imagen es más alta que el JLabel
            int scaledImageHeight = labelHeight;
            int scaledImageWidth = (int) (scaledImageHeight * originalImageAspectRatio);
            Image scaledImage = originalImageIcon.getImage().getScaledInstance(scaledImageWidth, scaledImageHeight, Image.SCALE_SMOOTH);
            scaledImageIcon = new ImageIcon(scaledImage);
        }
        lblimagen.setIcon(scaledImageIcon);
    }

    public void botoneSiguiente() {
        int i = 0;
        int posicionA = categorias.getSelectedIndex();
        if (posicionA == -1) {
            return;
        }
        ListaCircular c = ctrlUsuario.buscarUsuario(Principal.credencial.getNombre()).getCategoria().get(posicionA).getImgCategoria();
        if ((c.getSize() - 1) == -1) {
            System.out.println("XD");
            return;
        }
        cargarImgs((String) c.getNext());
        boxImagenes.setSelectedIndex(c.getIndex());
    }

    public void botoneAnterior() {
        int posicionA = categorias.getSelectedIndex();
        if (posicionA == -1) {
            return;
        }
        ListaCircular c = ctrlUsuario.buscarUsuario(Principal.credencial.getNombre()).getCategoria().get(posicionA).getImgCategoria();
        if ((c.getSize() - 1) == -1) {
            return;
        }
        cargarImgs((String) c.getPrevious());

        boxImagenes.setSelectedIndex(c.getIndex());
    }

    public void cambiarImg() {
        int posicionCategoria = categorias.getSelectedIndex();
        int posImagen = boxImagenes.getSelectedIndex();
        if (posImagen == -1) {
            return;
        }
        if (boxImagenes.getModel().getSize() != 0) {
            ListaCircular c = ctrlUsuario.buscarUsuario(Principal.credencial.getNombre()).getCategoria().get(posicionCategoria).getImgCategoria();
            if (c.getSize() != 0) {
                System.out.println(posicionCategoria + "//" + posImagen);
                cargarImgs((String) c.get(posImagen));
                c.setIndex(posImagen);
                totalI.setText(posImagen + 1 + "/" + c.getSize());
            }

        }

    }

    public void actualizar() {
        int posicionA = categorias.getSelectedIndex();
        if (posicionA == -1) {
            cargarImgs(null);
            nuevo = true;
            cargarImgCombo();
            return;
        }
        ListaCircular c = ctrlUsuario.buscarUsuario(Principal.credencial.getNombre()).getCategoria().get(posicionA).getImgCategoria();

        if (c.getSize() == 0) {
            cargarImgs(null);
            nuevo = true;
            cargarImgCombo();

        } else {
            cargarImgs((String) c.get(c.getIndex()));
            cargarImgCombo();
            boxImagenes.setSelectedIndex(c.getIndex());
            //cargarImgCombo();
        }
    }

    public void actualizarArchivo() {
        try {
            FileOutputStream archivoSalida = new FileOutputStream("C:\\Users\\mesoi\\Documents\\Prueba\\alumnos.txt");
            ObjectOutputStream objetoSalida = new ObjectOutputStream(archivoSalida);
            objetoSalida.writeObject(ctrlUsuario.obtenerLista());
            objetoSalida.close();
            archivoSalida.close();
            System.out.println("El ArrayList de Alumnos ha sido serializado y guardado en alumnos.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        nombreU = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        categorias = new javax.swing.JList<>();
        btnAgregarCategoria = new Elementos.ButtonRound();
        btnEliminarCategoria = new Elementos.ButtonRound();
        jSeparator1 = new javax.swing.JSeparator();
        btnCerrar = new Elementos.ButtonRound();
        panelRound2 = new Elementos.PanelRound();
        btnAgregarI = new Elementos.ButtonRound();
        btnEliminarI = new Elementos.ButtonRound();
        boxImagenes = new javax.swing.JComboBox<>();
        btnIzquierda = new javax.swing.JLabel();
        lblimagen = new javax.swing.JLabel();
        btnDerecha = new javax.swing.JLabel();
        totalI = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(31, 33, 37));

        panelRound1.setBackground(new java.awt.Color(39, 44, 51));
        panelRound1.setForeground(new java.awt.Color(39, 44, 51));
        panelRound1.setRoundBottomLeft(20);
        panelRound1.setRoundBottomRight(20);
        panelRound1.setRoundTopLeft(20);
        panelRound1.setRoundTopRight(20);

        nombreU.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        nombreU.setForeground(new java.awt.Color(255, 255, 255));
        nombreU.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nombreU.setText("Nombre");

        jPanel2.setOpaque(false);

        jLabel2.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Categorias");

        categorias.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        categorias.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                categoriasValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(categorias);

        btnAgregarCategoria.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarCategoria.setText("+");
        btnAgregarCategoria.setBorderColor(new java.awt.Color(37, 198, 121));
        btnAgregarCategoria.setColor(new java.awt.Color(37, 198, 121));
        btnAgregarCategoria.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        btnAgregarCategoria.setRadius(20);
        btnAgregarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarCategoriaActionPerformed(evt);
            }
        });

        btnEliminarCategoria.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarCategoria.setText("-");
        btnEliminarCategoria.setBorderColor(new java.awt.Color(251, 79, 87));
        btnEliminarCategoria.setColor(new java.awt.Color(251, 79, 87));
        btnEliminarCategoria.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        btnEliminarCategoria.setRadius(20);
        btnEliminarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarCategoriaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnAgregarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEliminarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                .addContainerGap())
        );

        jSeparator1.setForeground(new java.awt.Color(84, 88, 100));

        btnCerrar.setForeground(new java.awt.Color(255, 255, 255));
        btnCerrar.setText("Cerrar Sesión");
        btnCerrar.setBorderColor(new java.awt.Color(125, 124, 197));
        btnCerrar.setColor(new java.awt.Color(20, 26, 31));
        btnCerrar.setColorOver(new java.awt.Color(26, 35, 43));
        btnCerrar.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        btnCerrar.setRadius(20);
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(nombreU, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator1)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCerrar, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(nombreU, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        panelRound2.setBackground(new java.awt.Color(39, 44, 51));
        panelRound2.setForeground(new java.awt.Color(39, 44, 51));
        panelRound2.setRoundBottomLeft(20);
        panelRound2.setRoundBottomRight(20);
        panelRound2.setRoundTopLeft(20);
        panelRound2.setRoundTopRight(20);

        btnAgregarI.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarI.setBorderColor(new java.awt.Color(37, 198, 121));
        btnAgregarI.setColor(new java.awt.Color(37, 198, 121));
        btnAgregarI.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        btnAgregarI.setRadius(20);
        btnAgregarI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarIActionPerformed(evt);
            }
        });

        btnEliminarI.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarI.setBorderColor(new java.awt.Color(251, 79, 87));
        btnEliminarI.setColor(new java.awt.Color(251, 79, 87));
        btnEliminarI.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        btnEliminarI.setRadius(20);
        btnEliminarI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarIActionPerformed(evt);
            }
        });

        boxImagenes.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        boxImagenes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                boxImagenesItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(btnAgregarI, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEliminarI, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(boxImagenes, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(boxImagenes, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnEliminarI, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAgregarI, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        btnIzquierda.setForeground(new java.awt.Color(255, 255, 255));
        btnIzquierda.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnIzquierda.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnIzquierda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnIzquierdaMouseClicked(evt);
            }
        });

        lblimagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        btnDerecha.setBackground(new java.awt.Color(255, 255, 255));
        btnDerecha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnDerecha.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDerecha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDerechaMouseClicked(evt);
            }
        });

        totalI.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        totalI.setForeground(new java.awt.Color(255, 255, 255));
        totalI.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalI.setText("0/0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(panelRound2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnIzquierda, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblimagen, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDerecha, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(totalI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(panelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(btnIzquierda, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblimagen, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE))
                            .addComponent(btnDerecha, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                        .addComponent(totalI)))
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
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarCategoriaActionPerformed
        // TODO add your handling code here:
        agregarC();
    }//GEN-LAST:event_btnAgregarCategoriaActionPerformed

    private void btnEliminarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarCategoriaActionPerformed
        // TODO add your handling code here:
        eliminarC();
    }//GEN-LAST:event_btnEliminarCategoriaActionPerformed

    private void btnAgregarIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarIActionPerformed
        // TODO add your handling code here:
        agregarFoto();
    }//GEN-LAST:event_btnAgregarIActionPerformed

    private void btnDerechaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDerechaMouseClicked
        // TODO add your handling code here:
        botoneSiguiente();
    }//GEN-LAST:event_btnDerechaMouseClicked

    private void btnIzquierdaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIzquierdaMouseClicked
        // TODO add your handling code here:
        botoneAnterior();
    }//GEN-LAST:event_btnIzquierdaMouseClicked

    private void categoriasValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_categoriasValueChanged
        // TODO add your handling code here:
        actualizar();
    }//GEN-LAST:event_categoriasValueChanged

    private void btnEliminarIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarIActionPerformed
        // TODO add your handling code here:
        eliminarFoto();
    }//GEN-LAST:event_btnEliminarIActionPerformed

    private void boxImagenesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_boxImagenesItemStateChanged
        // TODO add your handling code here:
        cambiarImg();
    }//GEN-LAST:event_boxImagenesItemStateChanged

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        // TODO add your handling code here:
        Principal p = new Principal();
        p.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

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
            java.util.logging.Logger.getLogger(Biblioteca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Biblioteca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Biblioteca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Biblioteca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Biblioteca().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> boxImagenes;
    private Elementos.ButtonRound btnAgregarCategoria;
    private Elementos.ButtonRound btnAgregarI;
    private Elementos.ButtonRound btnCerrar;
    private javax.swing.JLabel btnDerecha;
    private Elementos.ButtonRound btnEliminarCategoria;
    private Elementos.ButtonRound btnEliminarI;
    private javax.swing.JLabel btnIzquierda;
    private javax.swing.JList<String> categorias;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblimagen;
    private javax.swing.JLabel nombreU;
    private Elementos.PanelRound panelRound1;
    private Elementos.PanelRound panelRound2;
    public static javax.swing.JLabel totalI;
    // End of variables declaration//GEN-END:variables
}
