package CustomTable;

import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.CellRendererPane;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.RowSorter;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.MouseInputListener;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicTableHeaderUI;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import org.apache.log4j.Logger;

public final class JBroTableHeaderUI extends BasicTableHeaderUI {
  private static final Logger LOGGER = Logger.getLogger( JBroTableHeaderUI.class );
  private static final Cursor resizeCursor = Cursor.getPredefinedCursor( Cursor.E_RESIZE_CURSOR );
  private static final Map< String, Boolean > EXISTING_PARENT_UIS = new HashMap<  >();
  
  private JBroTableColumn selectedColumn;
  private final JBroTable table;
  private List< ComponentUI > delegates;
  private List< CellRendererPane > rendererPanes;
  private List< JTableHeader > headers;
  private List< Integer > rowHeights;
  private boolean updating;
  private ComponentUI headerDelegate;
  
  public JBroTableHeaderUI( JBroTable table ) {
    this.table = table;
    updateLookAndFeel();
  }
  
  void updateLookAndFeel() {
    if ( updating )
      return;
    updating = true;
    if ( header != null ) {
      uninstallUI( header );
      installUI( header );
    }
    updating = false;
  }
  
  public JBroTableHeader getHeader() {
    return ( JBroTableHeader )header;
  }
  
  public JTableHeader getHeader( int level ) {
    return headers.get( level );
  }
  
  private CellRendererPane getRendererPane( ComponentUI ui ) {
    Object o = getField( "rendererPane", ui );
    if ( o instanceof CellRendererPane )
      return ( CellRendererPane )o;
    return null;
  }
  
  private Object getField( String fieldName, ComponentUI ui ) {
    if ( ui == null )
      return null;
    try {
      Field field = BasicTableHeaderUI.class.getDeclaredField( fieldName );
      boolean accessible = field.isAccessible();
      if ( !accessible )
        field.setAccessible( true );
      Object ret = ( Object )field.get( ui );
      if ( !accessible )
        field.setAccessible( false );
      return ret;
    } catch ( IllegalAccessException | IllegalArgumentException | NoSuchFieldException | SecurityException e ) {
      LOGGER.error( null, e );
    }
    return null;
  }
  
  private void setField( String fieldName, Object value, ComponentUI ui ) {
    if ( ui == null )
      return;
    try {
      Field field = BasicTableHeaderUI.class.getDeclaredField( fieldName );
      boolean accessible = field.isAccessible();
      if ( !accessible )
        field.setAccessible( true );
      field.set( ui, value );
      if ( !accessible )
        field.setAccessible( false );
    } catch ( IllegalAccessException | IllegalArgumentException | NoSuchFieldException | SecurityException e ) {
      LOGGER.error( null, e );
    }
  }
  
  private Object call( String methodName, ComponentUI ui ) {
    return call( methodName, null, ui, null );
  }
  
  private Object call( String methodName, Class parameters[], ComponentUI ui, Object args[] ) {
    return call( BasicTableHeaderUI.class, methodName, parameters, ui, args );
  }
  
  private Object call( Class clazz, String methodName, Class parameters[], ComponentUI ui, Object args[] ) {
    if ( ui == null )
      return null;
    try {
      Method method = parameters == null ? clazz.getDeclaredMethod( methodName ) : clazz.getDeclaredMethod( methodName, parameters );
      boolean accessible = method.isAccessible();
      if ( !accessible )
        method.setAccessible( true );
      return args == null ? method.invoke( ui ) : method.invoke( ui, args );
    } catch ( IllegalAccessException | IllegalArgumentException | NoSuchMethodException e ) {
      LOGGER.error( null, e );
    } catch ( InvocationTargetException e ) {
      LOGGER.error( null, e );
      LOGGER.error( null, ( ( InvocationTargetException )e ).getCause() );
    }
    return null;
  }

  @Override
  protected void rolloverColumnUpdated( int oldColumn, int newColumn ) {
      delegates.stream().forEach((delegate) -> {
          call( "rolloverColumnUpdated", new Class[]{ int.class, int.class }, delegate, new Object[]{ oldColumn, newColumn } );
      });
    super.rolloverColumnUpdated( oldColumn, newColumn );
  }

  @Override
  protected void uninstallKeyboardActions() {
    super.uninstallKeyboardActions();
    delegates.stream().forEach((delegate) -> {
        call( "uninstallKeyboardActions", delegate );
    });
  }

  @Override
  protected void uninstallListeners() {
    super.uninstallListeners();
    delegates.stream().forEach((delegate) -> {
        call( "uninstallListeners", delegate );
    });
  }

  @Override
  protected void uninstallDefaults() {
    super.uninstallDefaults();
    delegates.stream().forEach((delegate) -> {
        call( "uninstallDefaults", delegate );
    });
  }

  @Override
  public void uninstallUI( JComponent c ) {
    for ( int i = 0; i < delegates.size(); i++ )
      call( "uninstallUI", new Class[]{ JComponent.class }, delegates.get( i ), new Object[]{ headers.get( i ) } );
    call( "uninstallUI", new Class[]{ JComponent.class }, headerDelegate, new Object[]{ header } );
    super.uninstallDefaults();
    super.uninstallListeners();
    super.uninstallKeyboardActions();
    header.removeAll();
    rendererPane = null;
    delegates.clear();
    headers.clear();
    rendererPanes.clear();
  }

  @Override
  protected void installKeyboardActions() {
      delegates.stream().forEach((delegate) -> {
          call( "installKeyboardActions", delegate );
      });
    super.installKeyboardActions();
  }

  @Override
  protected void installListeners() {
      delegates.stream().forEach((delegate) -> {
          call( "installListeners", delegate );
      });
    super.installListeners();
    for ( MouseListener ml : header.getMouseListeners() )
      if ( ml instanceof BasicTableHeaderUI.MouseInputHandler )
        header.removeMouseListener( ml );
    for ( MouseMotionListener ml : header.getMouseMotionListeners() )
      if ( ml instanceof BasicTableHeaderUI.MouseInputHandler )
        header.removeMouseMotionListener( ml );
  }

  @Override
  protected void installDefaults() {
      delegates.stream().forEach((delegate) -> {
          call( "installDefaults", delegate );
      });
    super.installDefaults();
  }
  
  public void updateModel() {
    int levelsCnt = table.getData() == null ? 0 : table.getData().getFieldGroups().size();
    if ( levelsCnt <= delegates.size() )
      return;
    JBroTableHeader header = getHeader();
    try {
      Class uiClass = UIManager.getLookAndFeelDefaults().getUIClass( "TableHeaderUI" );
      Method createUImethod = uiClass.getMethod( "createUI", JComponent.class );
      headerDelegate = ( ComponentUI )createUImethod.invoke( null, header );
      call( "installUI", new Class[]{ JComponent.class }, headerDelegate, new Object[]{ header } );
      for ( int level = delegates.size(); level < levelsCnt; level++ ) {
        ComponentUI delegate = ( ComponentUI )createUImethod.invoke( null, header );
        delegates.add( delegate );
        JTableHeader levelHeader = header.createDelegateForLevel( level );
        call( "installUI", new Class[]{ JComponent.class }, delegate, new Object[]{ levelHeader } );
        headers.add( levelHeader );
        CellRendererPane delegateRendererPane = getRendererPane( delegate );
        rendererPanes.add( delegateRendererPane );
      }
    } catch ( IllegalAccessException e ) {
      LOGGER.error( null, e );
    } catch ( NoSuchMethodException e ) {
      LOGGER.error( null, e );
    } catch ( InvocationTargetException e ) {
      LOGGER.error( null, e );
      LOGGER.error( null, ( ( InvocationTargetException )e ).getCause() );
    }
  }

  @Override
  public void installUI( JComponent c ) {
    if ( !( c instanceof JBroTableHeader ) )
      throw new IllegalArgumentException( "Not a groupable header: " + c );
    JBroTableHeader header = ( JBroTableHeader )c;
    this.header = header;
    int levelsCnt = table.getData() == null ? 0 : table.getData().getFieldGroups().size();
    delegates = new ArrayList<  >( levelsCnt );
    headers = new ArrayList<  >( levelsCnt );
    rendererPanes = new ArrayList<  >( levelsCnt );
    updateModel();
    super.installUI( c );
    SwingUtilities.updateComponentTreeUI( header );
    headers.stream().forEach((delegateHeader) -> {
        SwingUtilities.updateComponentTreeUI( delegateHeader );
    });
  }
  
  @Override
  public void update( Graphics g, JComponent c ) {
      delegates.stream().forEach((delegate) -> {
          call( ComponentUI.class, "update", new Class[]{ Graphics.class, JComponent.class }, delegate, new Object[]{ g, c } );
      });
    super.update( g, c );
  }

  private TableCellRenderer getRenderer( TableColumn column ) {
    TableCellRenderer renderer = column.getHeaderRenderer();
    if ( renderer == null ) {
      if ( column instanceof JBroTableColumn ) {
        JBroTableColumn dtc = ( JBroTableColumn )column;
        renderer = headers.get( dtc.getY() ).getDefaultRenderer();
      } else
        renderer = header.getDefaultRenderer();
    }
    return renderer;
  }

  @Override
  public void paint( Graphics g, JComponent c ) {
    JBroTableColumnModel groupModel = getTableColumnModel();
    if ( groupModel == null )
      return;
    JBroTableHeader header = getHeader();
    int headerHeight = header.getSize().height;
    Rectangle cellRect = new Rectangle();
    JBroTableColumn draggedColumn = header.getDraggedGroup();
    Enumeration< TableColumn > enumeration = groupModel.getColumns();
    List< JBroTableColumn > currentColumns = new ArrayList<  >();
    List< Integer > coords = new ArrayList<  >();
    currentColumns.add( null );
    boolean draggedColumnMet = false;
    while ( enumeration.hasMoreElements() ) {
      JBroTableColumn column = ( JBroTableColumn )enumeration.nextElement();
      List< JBroTableColumn > columnParents = groupModel.getColumnParents( column, true );
      int level = 0;
      boolean addAbsent = false;
      boolean doNotPaintCells = draggedColumnMet && columnParents.contains( draggedColumn );
      for ( JBroTableColumn columnParent : columnParents ) {
        if ( !addAbsent ) {
          JBroTableColumn currentColumn = currentColumns.get( level );
          if ( columnParent != currentColumn ) {
            while ( currentColumns.size() > level )
              currentColumns.remove( level );
            while ( coords.size() > level )
              coords.remove( level );
            addAbsent = true;
          }
          level++;
        }
        if ( addAbsent ) {
          currentColumns.add( columnParent );
          cellRect.y = coords.isEmpty() ? 0 : coords.get( coords.size() - 1 );
          Dimension cellSize = getGroupSize( columnParent );
          if ( columnParent == column )
            cellSize.height = headerHeight - cellRect.y;
          cellRect.setSize( cellSize );
          if ( draggedColumn == columnParent ) {
            g.setColor( header.getParent().getBackground() );
            g.fillRect( cellRect.x, cellRect.y, cellRect.width, headerHeight - cellRect.y );
            draggedColumnMet = true;
            doNotPaintCells = true;
          } else if ( !doNotPaintCells )
            paintCell( g, cellRect, columnParent );
          if ( columnParent != column ) {
            cellRect.y += cellSize.height;
            coords.add( cellRect.y );
          }
        }
      }
      cellRect.x += cellRect.width;
    }
    if ( draggedColumn != null ) {
      Rectangle bounds = getGroupHeaderBoundsFor( draggedColumn );
      bounds.x += header.getDraggedDistance();
      paintWithChildren( g, groupModel, draggedColumn, bounds );
    }
    rendererPane.removeAll();
  }
  
  private void paintWithChildren( Graphics g, JBroTableColumnModel groupModel, JBroTableColumn parent, Rectangle initial ) {
    Rectangle bounds = new Rectangle( initial );
    g.setColor( header.getParent().getBackground() );
    g.fillRect( bounds.x, bounds.y, bounds.width, bounds.height );
    paintCell( g, bounds, parent );
    bounds.y += bounds.height;
    groupModel.getColumnChildren( parent ).stream().map((child) -> {
        bounds.setSize( getGroupSize( child ) );
        return child;
      }).map((child) -> {
          paintWithChildren( g, groupModel, child, bounds );
          return child;
      }).forEach((_item) -> {
          bounds.x += bounds.width;
      });
  }

  private void paintCell( Graphics g, Component component, Rectangle cellRect ) {
    rendererPane.add( component );
    rendererPane.paintComponent( g, component, header, cellRect.x, cellRect.y, cellRect.width, cellRect.height, true );
  }
  
  public boolean isLeaf( JBroTableColumn column ) {
    return column.getY() + column.getRowspan() == headers.size();
  }

  private void paintCell( Graphics g, Rectangle cellRect, JBroTableColumn group ) {
    TableCellRenderer renderer = getRenderer( group );
    boolean parentUIdeterminesRolloverColumnItself = hasParentUI( renderer );
    boolean rollover = parentUIdeterminesRolloverColumnItself ? group == getHeader().getDraggedGroup() : group == selectedColumn;
    table.setCurrentLevel( group.getY() );
    Component component = renderer.getTableCellRendererComponent( table, group.getHeaderValue(), rollover, rollover, group.getY(), getTableColumnModel().getColumnRelativeIndex( group ) );
    table.setCurrentLevel( null );
    paintCell( g, component, cellRect );
  }

  public Dimension getGroupSize( JBroTableColumn group ) {
    Dimension size = new Dimension();
    JBroTableColumnModel groupModel = getTableColumnModel();
    for ( int level = group.getY(); level < group.getY() + group.getRowspan(); level++ ) {
      if ( rowHeights != null && rowHeights.size() > level ) {
        Integer height = rowHeights.get( level );
        if ( height != null ) {
          size.height += height;
          continue;
        }
      }
      TableCellRenderer renderer = headers.get( level ).getDefaultRenderer();
      Component comp = renderer.getTableCellRendererComponent( header.getTable(), group.getHeaderValue(), false, false, -1, -1 );
      size.height += comp.getPreferredSize().height;
    }
    List< JBroTableColumn > children = groupModel.getColumnChildren( group );
    if ( children.isEmpty() )
      size.width = group.getWidth();
    else
        children.stream().forEach((column) -> {
            size.width += groupModel.getWidth( column );
    });
    return size;
  }

  private int calculateHeaderHeight() {
    int mHeight = 0;
    JBroTableColumnModel groupModel = getTableColumnModel();
    for ( int column = 0; column < groupModel.getColumnCount(); column++ ) {
      int cHeight = 0;
      JBroTableColumn parent = groupModel.getColumn( column );
      while ( parent != null ) {
        for ( int level = parent.getY(); level < parent.getY() + parent.getRowspan(); level++ ) {
          if ( rowHeights != null && rowHeights.size() > level ) {
            Integer height = rowHeights.get( level );
            if ( height != null ) {
              cHeight += height;
              continue;
            }
          }
          TableCellRenderer renderer = headers.get( level ).getDefaultRenderer();
          Component comp = renderer.getTableCellRendererComponent( header.getTable(), parent.getHeaderValue(), false, false, -1, column );
          cHeight += comp.getPreferredSize().height;
        }
        parent = groupModel.getColumnParent( parent );
      }
      mHeight = Math.max( mHeight, cHeight );
    }
    return mHeight;
  }

  public int getRowHeight( int level ) {
    int extra = level == headers.size() - 1 ? getHeader().getPreferredSize().height - calculateHeaderHeight() : 0;
    if ( rowHeights != null && rowHeights.size() > level ) {
      Integer height = rowHeights.get( level );
      if ( height != null )
        return extra + height;
    }
    int mHeight = 0;
    JBroTableColumnModel groupModel = getTableColumnModel();
    for ( int column = 0; column < groupModel.getColumnCount(); column++ ) {
      JBroTableColumn parent = groupModel.getColumn( column );
      while ( parent != null ) {
        if ( parent.getY() <= level && level < parent.getY() + parent.getRowspan() ) {
          TableCellRenderer renderer = headers.get( level ).getDefaultRenderer();
          Component comp = renderer.getTableCellRendererComponent( header.getTable(), parent.getHeaderValue(), false, false, -1, column );
          mHeight = Math.max( mHeight, comp.getPreferredSize().height );
        }
        parent = groupModel.getColumnParent( parent );
      }
    }
    return extra + mHeight;
  }

  @Override
  public Dimension getPreferredSize( JComponent c ) {
    int width = 0;
    Enumeration enumeration = header.getColumnModel().getColumns();
    while ( enumeration.hasMoreElements() ) {
      JBroTableColumn aColumn = ( JBroTableColumn )enumeration.nextElement();
      width += aColumn.getPreferredWidth();
    }
    return new Dimension( width, calculateHeaderHeight() );
  }

  private void selectColumn( JBroTableColumn newSelectedColumn ) {
    if ( selectedColumn != newSelectedColumn ) {
      JBroTableColumn oldSelectedColumn = selectedColumn;
      selectedColumn = newSelectedColumn;
      if ( oldSelectedColumn != null ) {
        Rectangle repaintRect = getGroupHeaderBoundsFor( oldSelectedColumn );
        header.repaint( repaintRect );
      }
      if ( newSelectedColumn != null ) {
        Rectangle repaintRect = getGroupHeaderBoundsFor( newSelectedColumn );
        header.repaint( repaintRect );
      }
      JBroTableColumnModel columnModel = getTableColumnModel();
      rolloverColumnUpdated( columnModel.getColumnAbsoluteIndex( oldSelectedColumn ), columnModel.getColumnAbsoluteIndex( newSelectedColumn ) );
      if ( oldSelectedColumn != null && newSelectedColumn != null && oldSelectedColumn.getY() != newSelectedColumn.getY() ) {
        BasicTableHeaderUI parentUI = getParentUI( getRenderer( oldSelectedColumn ) );
        setField( "rolloverColumn", -1, parentUI );
      }
      if ( oldSelectedColumn != null || newSelectedColumn != null ) {
        BasicTableHeaderUI parentUI = getParentUI( getRenderer( newSelectedColumn == null ? oldSelectedColumn : newSelectedColumn ) );
        setField( "rolloverColumn", columnModel.getColumnRelativeIndex( newSelectedColumn ), parentUI );
      }
    }
  }
  
  protected static boolean hasParentUI( TableCellRenderer renderer ) {
    if ( renderer == null )
      return false;
    Class clazz = renderer.getClass();
    String className = clazz.getName();
    Boolean parentUIexists = EXISTING_PARENT_UIS.get( className );
    if ( parentUIexists != null )
      return parentUIexists;
    getParentUI( renderer );
    parentUIexists = EXISTING_PARENT_UIS.get( className );
    if ( parentUIexists != null )
      return parentUIexists;
    return false;
  }
  
  private static BasicTableHeaderUI getParentUI( TableCellRenderer renderer ) {
    if ( renderer == null )
      return null;
    Class clazz = renderer.getClass();
    String className = clazz.getName();
    try {
      Boolean parentUIexists = EXISTING_PARENT_UIS.get( className );
      if ( parentUIexists != null && !parentUIexists )
        return null;
      Field field = clazz.getDeclaredField( "this$0" );
      boolean accessible = field.isAccessible();
      if ( !accessible )
        field.setAccessible( true );
      Object ret = field.get( renderer );
      if ( !accessible )
        field.setAccessible( false );
      if ( ret instanceof BasicTableHeaderUI ) {
        EXISTING_PARENT_UIS.put( className, Boolean.TRUE );
        return ( BasicTableHeaderUI )ret;
      }
    } catch ( NoSuchFieldException e ) {
      // do nothing
    } catch ( SecurityException | IllegalArgumentException | IllegalAccessException e ) {
      LOGGER.error( null, e );
    }
    EXISTING_PARENT_UIS.put( className, Boolean.FALSE );
    return null;
  }

  private JBroTableColumnModel getTableColumnModel() {
    return ( JBroTableColumnModel )header.getColumnModel();
  }

  private boolean canResize( Point p, JBroTableColumn column, JTableHeader header ) {
    Rectangle bounds = getGroupHeaderBoundsFor( column );
    bounds.grow( -3, 0 );
    return column != null && !bounds.contains( p ) && header.getResizingAllowed() && column.getResizable();
  }

  private void updateRolloverColumn( MouseEvent e ) {
    Point point = e.getPoint();
    if ( getHeader().getDraggedGroup() == null && header.contains( point ) ) {
      JBroTableColumn column = getColumnAtPoint( point );
      selectColumn( column );
    }
  }
  
  public JBroTableColumn getColumnAtPoint( Point point ) {
    int col = header.columnAtPoint( point );
    int level = getRowAtPoint( point );
    return getTableColumnModel().getColumnAtAbsolutePosition( col, level );
  }

  private int changeColumnWidth( JBroTableColumn resizingColumn, JTableHeader th, int oldWidth, int newWidth ) {
    resizingColumn.setWidth( newWidth );
    Container container;
    JTable table;
    if ( th.getParent() == null
         || ( container = th.getParent().getParent() ) == null
         || !( container instanceof JScrollPane )
         || ( table = th.getTable() ) == null )
      return 0;
    if ( !container.getComponentOrientation().isLeftToRight() && !th.getComponentOrientation().isLeftToRight() ) {
      JViewport viewport = ( ( JScrollPane )container ).getViewport();
      int viewportWidth = viewport.getWidth();
      int diff = newWidth - oldWidth;
      int newHeaderWidth = table.getWidth() + diff;
      Dimension tableSize = table.getSize();
      tableSize.width += diff;
      table.setSize( tableSize );
      if ( newHeaderWidth >= viewportWidth && table.getAutoResizeMode() == JTable.AUTO_RESIZE_OFF ) {
        Point p = viewport.getViewPosition();
        p.x = Math.max( 0, Math.min( newHeaderWidth - viewportWidth, p.x + diff ) );
        viewport.setViewPosition( p );
        return diff;
      }
    }
    return 0;
  }

  private int viewIndexForColumn( JBroTableColumn aColumn ) {
    JBroTableColumnModel cm = getHeader().getColumnModel();
    for ( int column = 0; column < cm.getColumnCount(); column++ )
      if ( cm.getColumn( column ) == aColumn )
        return column;
    return -1;
  }
  
  public void setRowHeight( int level, Integer height ) {
    if ( rowHeights == null )
      rowHeights = new ArrayList<  >( level + 1 );
    while ( rowHeights.size() <= level )
      rowHeights.add( null );
    rowHeights.set( level, height );
  }

  public Rectangle getGroupHeaderBoundsFor( JBroTableColumn group ) {
    if ( group == null )
      return new Rectangle();
    JBroTableColumnModel columnModel = getTableColumnModel();
    Dimension size = getGroupSize( group );
    Rectangle bounds = new Rectangle( size );
    bounds.y = 0;
    columnModel.getColumnParents( group, false ).stream().forEach((parent) -> {
        bounds.y += getGroupSize( parent ).height;
    });
    int lastColumnIndex = columnModel.getColumnIndex( group.getIdentifier() );
    for ( int index = 0; index < lastColumnIndex; index++ ) {
      JBroTableColumn tc = columnModel.getColumn( index );
      bounds.x += tc.getWidth();
    }
    return bounds;
  }
  
  public int getRowAtPoint( Point point ) {
    int y = 0;
    for ( int level = 0; level < headers.size(); level++ ) {
      y += getRowHeight( level );
      if ( y > point.y )
        return level;
    }
    return -1;
  }

  @Override
	protected MouseInputListener createMouseInputListener() {
		return new MouseInputHandler();
	}

  public class MouseInputHandler implements MouseInputListener {
    private int mouseXOffset;
    private Cursor otherCursor = resizeCursor;

    @Override
    public void mouseClicked( MouseEvent e ) {
      JBroTableHeader header = getHeader();
      if ( !header.isEnabled() )
        return;
      Point point = e.getPoint();
      JBroTableColumn column = getColumnAtPoint( point );
      if ( column == null )
        return;
      if ( isLeaf( column ) && e.getClickCount() == 1 && SwingUtilities.isLeftMouseButton( e ) ) {
        JTable table = header.getTable();
        RowSorter sorter;
        if ( table != null && ( sorter = table.getRowSorter() ) != null ) {
          int columnIndex = column.getModelIndex();
          if ( columnIndex != -1 ) {
            sorter.toggleSortOrder( columnIndex );
            header.repaint();
          }
        }
      }
    }

    private JBroTableColumn getResizingColumn( Point p ) {
      int row = getRowAtPoint( p );
      if ( row == -1 )
        return null;
      int column = getResizingColumnIndex( p );
      if ( column == -1 )
        return null;
      JBroTableColumnModel columnModel = getTableColumnModel();
      return columnModel.getColumnAtAbsolutePosition( column, row );
    }

    private int getResizingColumnIndex( Point p ) {
      int row = getRowAtPoint( p );
      if ( row == -1 )
        return -1;
      int column = header.columnAtPoint( p );
      if ( column == -1 )
        return -1;
      JBroTableHeader header = getHeader();
      JBroTableColumnModel columnModel = getTableColumnModel();
      JBroTableColumn dtc = columnModel.getColumnAtAbsolutePosition( column, row );
      Rectangle r = getGroupHeaderBoundsFor( dtc );
      r.grow( -3, 0 );
      if ( r.contains( p ) )
        return -1;
      int midPoint = r.x + r.width / 2;
      int columnIndex;
      if ( header.getComponentOrientation().isLeftToRight() )
        columnIndex = p.x < midPoint ? column - 1 : column;
      else
        columnIndex = p.x < midPoint ? column : column - 1;
      return columnIndex;
    }

    @Override
    public void mousePressed( MouseEvent e ) {
      if ( !header.isEnabled() )
        return;
      header.setDraggedColumn( null );
      header.setResizingColumn( null );
      header.setDraggedDistance( 0 );
      Point point = e.getPoint();
      int idx = getResizingColumnIndex( point );
      JBroTableColumn resizingColumn = idx < 0 ? null : getTableColumnModel().getColumn( idx );
      if ( canResize( point, resizingColumn, header ) ) {
        header.setResizingColumn( resizingColumn );
        if ( header.getComponentOrientation().isLeftToRight() )
          mouseXOffset = point.x - getGroupSize( resizingColumn ).width;
        else
          mouseXOffset = point.x + getGroupSize( resizingColumn ).width;
      } else if ( header.getReorderingAllowed() ) {
        JBroTableColumn column = getColumnAtPoint( point );
        if ( column != null ) {
          header.setDraggedColumn( column );
          mouseXOffset = point.x;
          selectColumn( null );
        }
      }
    }

    @Override
    public void mouseMoved( MouseEvent e ) {
      if ( !header.isEnabled() )
        return;
      Point point = e.getPoint();
      JBroTableColumn selectedColumn = getColumnAtPoint( point );
      selectColumn( selectedColumn );
      JBroTableColumn resizingColumn = getResizingColumn( point );
      Cursor cursor = header.getCursor();
      if ( canResize( point, resizingColumn, header ) ) {
        if ( cursor != resizeCursor ) {
          header.setCursor( resizeCursor );
          otherCursor = cursor;
        }
      } else if ( cursor == resizeCursor )
        header.setCursor( otherCursor == resizeCursor ? null : otherCursor );
      updateRolloverColumn( e );
    }

    @Override
    public void mouseDragged( MouseEvent e ) {
      JBroTableHeader header = getHeader();
      if ( !header.isEnabled() )
        return;
      int mouseX = e.getX();
      JBroTableColumn resizingColumn = header.getResizingColumn();
      JBroTableColumn draggedColumn = header.getDraggedGroup();
      boolean headerLeftToRight = header.getComponentOrientation().isLeftToRight();
      JBroTableColumnModel groupModel = getTableColumnModel();
      if ( draggedColumn != null ) {
        boolean calcNewPosition = true;
        int draggedDistance = mouseX - mouseXOffset;
        boolean moved = false;
        while ( calcNewPosition ) {
          int startIndex = groupModel.getColumnAbsoluteIndex( draggedColumn );
          int endIndex = startIndex + draggedColumn.getColspan() - 1;
          int direction = draggedDistance < 0 ? -1 : 1;
          int newColumnIndex = direction < 0 ? startIndex - 1 : endIndex + 1;
          boolean shouldMove = true;
          if ( newColumnIndex < 0 ) {
            newColumnIndex = 0;
            shouldMove = false;
          } else if ( newColumnIndex >= groupModel.getColumnCount() ) {
            newColumnIndex = groupModel.getColumnCount() - 1;
            shouldMove = false;
          }
          if ( shouldMove ) {
            IModelFieldGroup modelField = groupModel.getModelField( draggedColumn );
            if ( modelField != null && !modelField.isManageable() ) {
              newColumnIndex = direction < 0 ? startIndex : endIndex;
              shouldMove = false;
            }
          }
          if ( shouldMove ) {
            JBroTableColumn parent = groupModel.getColumnParent( draggedColumn );
            if ( parent != null ) {
              int parentStartIndex = groupModel.getColumnAbsoluteIndex( parent );
              int parentEndIndex = parentStartIndex + parent.getColspan() - 1;
              if ( newColumnIndex < parentStartIndex ) {
                newColumnIndex = parentStartIndex;
                shouldMove = false;
              } else if ( newColumnIndex > parentEndIndex ) {
                newColumnIndex = parentEndIndex;
                shouldMove = false;
              }
            }
          }
          JBroTableColumn newGroup = null;
          if ( shouldMove ) {
            newGroup = groupModel.getColumnAtAbsolutePosition( newColumnIndex, draggedColumn.getY() );
            IModelFieldGroup modelField = groupModel.getModelField( newGroup );
            if ( modelField != null && !modelField.isManageable() )
              shouldMove = false;
          }
          if ( shouldMove ) {
            int width = getGroupSize( newGroup ).width;
            int groupStartIndex = groupModel.getColumnAbsoluteIndex( newGroup );
            int groupEndIndex = newGroup.getColspan() + groupStartIndex - 1;
            if ( direction < 0 )
              newColumnIndex = groupStartIndex;
            else
              newColumnIndex = groupEndIndex;
            if ( Math.abs( draggedDistance ) > width / 2 ) {
              if ( newColumnIndex >= 0 && newColumnIndex < groupModel.getColumnCount() ) {
                mouseXOffset += direction * width;
                draggedDistance = mouseX - mouseXOffset;
                groupModel.moveColumn( draggedColumn, newColumnIndex );
                moved = true;
              } else
                calcNewPosition = false;
            } else
              calcNewPosition = false;
          } else {
            draggedDistance = 0;
            calcNewPosition = false;
          }
        }
        header.setDraggedDistance( draggedDistance );
        if ( !moved )
          repaintHeaderAndTable();
      } else if ( resizingColumn != null ) {
        // TODO: child column resizing should affect only columns inside a parent group.
        // TODO: parent column resizing should proportionally affect all child columns.
        int oldWidth = getGroupSize( resizingColumn ).width;
        int newWidth;
        if ( headerLeftToRight )
          newWidth = mouseX - mouseXOffset;
        else
          newWidth = mouseXOffset - mouseX;
        mouseXOffset += changeColumnWidth( resizingColumn, header, oldWidth, newWidth );
      }
      updateRolloverColumn( e );
    }
    
    private void repaintHeaderAndTable() {
      Container c = table;
      while ( c != null ) {
        if ( SwingUtilities.isDescendingFrom( header, c ) )
          break;
        c = c.getParent();
      }
      if ( c == null ) {
        header.repaint();
        table.repaint();
      } else {
        Rectangle r = SwingUtilities.convertRectangle( header, header.getVisibleRect(), c );
        r = r.union( SwingUtilities.convertRectangle( table, table.getVisibleRect(), c ) );
        c.repaint( r.x, r.y, r.width, r.height );
      }
    }

    @Override
    public void mouseReleased( MouseEvent e ) {
      JBroTableHeader header = getHeader();
      if ( !header.isEnabled() )
        return;
      header.setDraggedDistance( 0 );
      header.setResizingColumn( null );
      header.setDraggedColumn( null );
      updateRolloverColumn( e );
      repaintHeaderAndTable();
    }

    @Override
    public void mouseEntered( MouseEvent e ) {
      if ( !header.isEnabled() )
        return;
      updateRolloverColumn( e );
    }

    @Override
    public void mouseExited( MouseEvent e ) {
      if ( !header.isEnabled() )
        return;
      selectColumn( null );
    }
  }
}