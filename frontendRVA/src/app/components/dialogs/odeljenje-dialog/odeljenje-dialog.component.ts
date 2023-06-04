import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Subscription } from 'rxjs';
import { Bolnica } from 'src/app/models/bolnica'
import { Odeljenje } from 'src/app/models/odeljenje'
import { BolnicaService } from 'src/app/services/bolnica.service';
import { OdeljenjeService } from 'src/app/services/odeljenje.service';

@Component({
    selector: 'app-odeljenje-dialog',
    templateUrl: './odeljenje-dialog.component.html',
    styleUrls: ['./odeljenje-dialog.component.css']
  })

  export class OdeljenjeDialogComponent{

    public flagOdelDialog!:number;
    public bolnice!: Bolnica[];//zbog combo-boxa 
    private subscription!:Subscription;

    constructor(public snackBar: MatSnackBar,
        public dialogRef: MatDialogRef<OdeljenjeDialogComponent>,
        @Inject(MAT_DIALOG_DATA) public data: Odeljenje,
        public odeljenjeService: OdeljenjeService,
        public bolnicaService: BolnicaService) { }

    ngOnInit() {
        this.subscription = this.bolnicaService.getAllBolnica().subscribe(data => { this.bolnice = data });
        }//podaci koje vrati getAll() metoda cemo smestiti u niz bolnica 
        
    compareTo(a: any, b: any) {
        return a.id == b.id;
    }

    public add(): void {
        this.odeljenjeService.addOdeljenje(this.data).subscribe(() => {
          this.snackBar.open('Uspesno dodato odeljenje: ' + this.data.naziv, 'OK', {
            duration: 2500
          })
        }),
          (error: Error) => {
            console.log(error.name + ' ' + error.message)
            this.snackBar.open('Doslo je do greske prilikom dodavanja novog odeljenja. ', 'Zatvori', {
              duration: 2500
            })
          };
      }

      public update(): void {
        this.odeljenjeService.updateOdeljenje(this.data).subscribe(() => {
          this.snackBar.open('Uspesno izmenjeno odeljenje: ' + this.data.naziv, 'OK', {
            duration: 2500
          })
        }),
          (error: Error) => {
            console.log(error.name + ' ' + error.message)
            this.snackBar.open('Doslo je do greske prilikom izmene odeljenja. ', 'Zatvori', {
              duration: 2500
            })
          };
    
      }

      public delete(): void {
        this.odeljenjeService.deleteOdeljenje(this.data.id).subscribe(() => {
          this.snackBar.open('Uspesno obrisano odeljenje: ' + this.data.id, 'OK', {
            duration: 2500
          })
        }),
          (error: Error) => {
            console.log(error.name + ' ' + error.message)
            this.snackBar.open('Doslo je do greske prilikom brisanja odeljenja. ', 'Zatvori', {
              duration: 2500
            })
          };
      }
    
      public cancel(): void {
        this.dialogRef.close();
        this.snackBar.open('Odustali ste od izmene. ', 'Zatvori', {
          duration: 1000
        })
      }
  }