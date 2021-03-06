import {Component} from '@angular/core';
import {fadeInAnimation} from './animations/fadeinanimation';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  animations: [fadeInAnimation],
  host: {'[@fadeInAnimation]': ''}
})
export class AppComponent {
}
