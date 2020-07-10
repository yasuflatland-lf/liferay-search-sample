package custom.order.search.contributor;

import com.liferay.portal.search.web.portlet.shared.search.PortletSharedSearchContributor;

import com.liferay.portal.search.web.portlet.shared.search.PortletSharedSearchSettings;
import custom.order.search.contributor.constants.CustomOrderSearchContributorPortletKeys;

import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true,
	property = "javax.portlet.name=" + CustomOrderSearchContributorPortletKeys.CUSTOM_ORDER_SEARCH_CONTRIBUTOR_PORTLET,
	service = PortletSharedSearchContributor.class
)
public class CustomOrderSharedSearchContributor
	implements PortletSharedSearchContributor {

	@Override
	public void contribute(
		PortletSharedSearchSettings portletSharedSearchSettings) {

	}
}
