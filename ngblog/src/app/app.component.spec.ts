import {async, TestBed} from '@angular/core/testing';
import {HttpClientModule} from '@angular/common/http';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {BrowserModule} from '@angular/platform-browser';
import {TopbarComponent} from './topbar/topbar.component';
import {APP_BASE_HREF} from '@angular/common';
import {FormsModule} from '@angular/forms';
// primeng
import {ButtonModule, FieldsetModule, PanelModule} from 'primeng/primeng';
// app
import {PostsComponent} from './posts/posts.component';
import {PostDetailsComponent} from './post-details/post-details.component';
import {PostsService} from './posts/posts.service';
import {AppRoutingModule} from './/app-routing.module';
import {AppComponent} from './app.component';
import {PostDetailsService} from './post-details/post-details.service';


describe('AppComponent', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        AppComponent,
        PostsComponent,
        PostDetailsComponent,
        TopbarComponent
      ],
      imports: [
        BrowserModule,
        BrowserAnimationsModule,
        FormsModule,
        HttpClientModule,
        ButtonModule,
        FieldsetModule,
        PanelModule,
        AppRoutingModule
      ],
      providers: [{provide: APP_BASE_HREF, useValue: '/'},
        PostsService,
        PostDetailsService]
    }).compileComponents();
  }));
  it('should create the app', async(() => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  }));
});
