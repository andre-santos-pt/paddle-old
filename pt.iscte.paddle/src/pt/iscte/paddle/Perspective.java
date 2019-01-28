package pt.iscte.paddle;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.IViewLayout;

public class Perspective implements IPerspectiveFactory {

	@Override	
	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(true);
		
		layout.setFixed(false);
//		layout.addView("pt.iscte.paddle.exercisesview", IPageLayout.BOTTOM, .8f, IPageLayout.ID_EDITOR_AREA);
		layout.addStandaloneView("org.eclipse.ui.navigator.ProjectExplorer", true, IPageLayout.LEFT, .2f, IPageLayout.ID_EDITOR_AREA);
//		layout.addStandaloneView("pt.iscte.paddle.debugview", true, IPageLayout.RIGHT, .4f, IPageLayout.ID_EDITOR_AREA);
//		layout.addView("pt.iscte.pandionj.view", IPageLayout.RIGHT, .5f, IPageLayout.ID_EDITOR_AREA);
//		layout.addView("org.eclipse.ui.views.ResourceNavigator", IPageLayout.LEFT, .2f, IPageLayout.ID_EDITOR_AREA);
		
		layout.getViewLayout("org.eclipse.ui.navigator.ProjectExplorer").setCloseable(false);
//		layout.getViewLayout("pt.iscte.paddle.debugview").setCloseable(false);

	}
}
