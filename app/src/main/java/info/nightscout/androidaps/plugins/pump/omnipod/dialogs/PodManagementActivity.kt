package info.nightscout.androidaps.plugins.pump.omnipod.dialogs

import android.content.Intent
import android.os.Bundle
import com.atech.android.library.wizardpager.WizardPagerActivity
import com.atech.android.library.wizardpager.WizardPagerContext
import com.atech.android.library.wizardpager.data.WizardPagerSettings
import com.atech.android.library.wizardpager.defs.WizardStepsWayType
import info.nightscout.androidaps.MainApp
import info.nightscout.androidaps.R
import info.nightscout.androidaps.activities.NoSplashActivity
import info.nightscout.androidaps.plugins.pump.omnipod.driver.comm.AapsOmnipodManager
import info.nightscout.androidaps.plugins.pump.omnipod.dialogs.wizard.initpod.InitPodCancelAction
import info.nightscout.androidaps.plugins.pump.omnipod.dialogs.wizard.initpod.InitPodWizardModel
import info.nightscout.androidaps.plugins.pump.omnipod.dialogs.wizard.removepod.RemovePodWizardModel
import info.nightscout.androidaps.utils.OKDialog
import kotlinx.android.synthetic.main.omnipod_pod_mgmt.*

class PodManagementActivity : NoSplashActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.omnipod_pod_mgmt)

        initpod_init_pod.setOnClickListener {
            initPodAction()
        }

        initpod_remove_pod.setOnClickListener {
            removePodAction()
        }

        initpod_reset_pod.setOnClickListener {
            resetPodAction()
        }


        initpod_pod_history.setOnClickListener {
            showPodHistory()
        }

    }


    fun initPodAction() {

        // TODO check if RL is running and that pod is not active

        val pagerSettings = WizardPagerSettings()

        pagerSettings.setWizardStepsWayType(WizardStepsWayType.CancelNext)
        pagerSettings.setFinishStringResourceId(R.string.close)
        pagerSettings.setFinishButtonBackground(R.drawable.finish_background)
        pagerSettings.setNextButtonBackground(R.drawable.selectable_item_background)
        pagerSettings.setBackStringResourceId(R.string.cancel)
        pagerSettings.setCancelAction(InitPodCancelAction())

        val wizardPagerContext = WizardPagerContext.getInstance();

        wizardPagerContext.clearContext()
        wizardPagerContext.pagerSettings = pagerSettings
        wizardPagerContext.wizardModel = InitPodWizardModel(applicationContext)

        val myIntent = Intent(this@PodManagementActivity, WizardPagerActivity::class.java)
        this@PodManagementActivity.startActivity(myIntent)
    }

    fun removePodAction() {
        // TODO check that pod is active

        val pagerSettings = WizardPagerSettings()

        pagerSettings.setWizardStepsWayType(WizardStepsWayType.CancelNext)
        pagerSettings.setFinishStringResourceId(R.string.close)
        pagerSettings.setFinishButtonBackground(R.drawable.finish_background)
        pagerSettings.setNextButtonBackground(R.drawable.selectable_item_background)
        pagerSettings.setBackStringResourceId(R.string.cancel)
        pagerSettings.setCancelAction(InitPodCancelAction())

        val wizardPagerContext = WizardPagerContext.getInstance();

        wizardPagerContext.clearContext()
        wizardPagerContext.pagerSettings = pagerSettings
        wizardPagerContext.wizardModel = RemovePodWizardModel(applicationContext)

        val myIntent = Intent(this@PodManagementActivity, WizardPagerActivity::class.java)
        this@PodManagementActivity.startActivity(myIntent)

    }

    fun resetPodAction() {
        // TODO check that pod is active

        OKDialog.showConfirmation(this,
                MainApp.gs(R.string.omnipod_cmd_reset_pod_desc), Thread {
            AapsOmnipodManager.getInstance().resetPodStatus()
        })
    }

    fun showPodHistory() {
        OKDialog.showConfirmation(this,
                MainApp.gs(R.string.omnipod_cmd_pod_history_na), null)
    }

}