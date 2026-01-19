import { animate, group, query, style, transition, trigger } from "@angular/animations";
import { AbsoluteSourceSpan } from "@angular/compiler";

export const slideInAnimation = trigger('slideInAnimation', [
    transition('* <=> *', [
        query(':enter, :leave', style({ position: 'fixed', width: '100%', zIndex:'2'}), { optional:true}),
        group([
            query(':enter', [ 
                style({transform: 'translateX(100%)'}), 
                animate('0.5s ease-out', style({transform:'translateX(0%)'}))
            
            
            ], {optional: true}), 
            query(':leave', [
                style({transform: 'translateX(0%)'}),
                animate('0.5s ease-out', style({transform:'translateX(-100%)'}))
            ], {optional: true})
        ])
    ])
]); 

export const sliderSmoothly = 
    trigger('routeAnimations', [
        transition('hoja_1 => hoja_2, hoja_2 => hoja_3', [
            /* Stilizado comun del elemento que entra y sale */
            query(':enter, :leave', [
                style({
                    position: 'absolute',
                    width: '100%',
                    opacity: .5,
                    top: '50%',
                    left: '50%',
                    transform: 'translate(100vw, -50%) scale(.1)'
                })
            ]),
            query(':leave', [
                style({
                    opacity:1,
                    transform: 'translate(-50%, -50%) scale(1)'
                })
            ]),
            group([
                query(':enter', [
                    animate('1000ms ease-in-out',
                    style({ 
                        opacity:1,
                        transform: 'translate(-50%, -50%) scale(1)' })
                    )
                ]),
                query(':leave', [
                    animate('1000ms ease-in-out',
                    style({ 
                        opacity:.5,
                        transform: 'translate(-100vw, -50%) scale(.1)' })
                    )
                ])
        ])
            
        ]),
        transition('hoja_3 => hoja_2, hoja_2 => hoja_1', [
            /* Stilizado comun del elemento que entra y sale */
            query(':enter, :leave', [
                style({
                    position: 'absolute',
                    width: '100%',
                    opacity: .5,
                    left: '50%',
                    top: '50%',
                    transform: 'translateX(-100vw) scale(.2)'
                })
            ]),
            query(':leave', [
                style({
                    opacity: 1,
                    transform: 'translate(-50%, -50%) scale(1)'
                })
            ]),
            group([
                query(':enter', [
                    animate('1000ms ease-in-out',
                        style({ 
                            opacity:1,
                            transform: 'translate(-50%, -50%) scale(1)' })
                        )]),
                query(':leave', [
                    animate('1000ms ease-in-out',
                        style({ 
                            opacity:.5,
                            transform: 'translate(100vw, -50%) scale(.1)' })
                        )])
            ])
            
        ])
    ])