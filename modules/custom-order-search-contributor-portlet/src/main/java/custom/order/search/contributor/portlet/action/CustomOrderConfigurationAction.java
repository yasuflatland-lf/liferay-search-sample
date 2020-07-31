package custom.order.search.contributor.portlet.action;

import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import custom.order.search.contributor.constants.CustomOrderSearchContributorPortletKeys;
import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true,
	property = "javax.portlet.name=" +
				CustomOrderSearchContributorPortletKeys.CUSTOM_ORDER_SEARCH_CONTRIBUTOR_PORTLET,
	service = ConfigurationAction.class
)
public class CustomOrderConfigurationAction
	extends DefaultConfigurationAction {
}
