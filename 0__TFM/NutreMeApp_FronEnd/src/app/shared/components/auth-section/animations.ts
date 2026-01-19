import { transition, trigger, style, animate } from "@angular/animations";

export const messageAnimations = trigger('alternate-message', [
    transition(':enter', [
        style({
            opacity:0,
            position: 'absolute',
            left: '-100%',
            width: '100%'
            
        }),
        animate('1s ease-in-out', style({
            opacity:1,
            left: '50%',
            transform: 'translateX(-50%)',
            width: '100%'
        }))
    ]),
    transition(':leave', [
        style({
            opacity:1,
            position: 'absolute',
            left: '50%',
            transform: 'translateX(-50%)',
            width: '100%'
            
        }),
        animate('.7s ease-in-out', style({
            opacity:0,
            left: '-100%',
            transform: 'translateX(0%)',
            width: '100%'
        }))
    ])
])