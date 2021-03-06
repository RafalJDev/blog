import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {MessageService} from 'primeng/components/common/messageservice';
import {Router} from '@angular/router';

@Injectable()
export class LoginService {
  private loginUrl = 'http://localhost:8080/api/login';

  constructor(private http: HttpClient,
              private messageService: MessageService,
              private router: Router) {
  }

  login(username: string, password: string) {
    this.http.post(this.loginUrl, {username, password})
      .subscribe(
        () => localStorage.setItem('currentUser', 'Basic ' + btoa(username + ':' + password)),
        () => this.messageService.add({severity: 'error', summary: 'Login failure...'}),
        () => {
          this.messageService.add({severity: 'success', summary: 'Login successful.'});
          this.router.navigate(['/adminPanel']);
        }
      );
  }

  getAuthorizationHeader() {
    return localStorage.getItem('currentUser');
  }

  logout() {
    localStorage.removeItem('currentUser');
  }

  isAuthenticated() {
    return this.getAuthorizationHeader();
  }
}
