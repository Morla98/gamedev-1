import { Component } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import translationsDe from '../assets/i18n/de.json';
import translationsEn from '../assets/i18n/en.json';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  constructor(translateService: TranslateService) {
    translateService.setTranslation('en', translationsEn);
    translateService.setTranslation('de', translationsDe);
    translateService.setDefaultLang('en');
  }
  title = 'super gameDevApp';
}
